package impls.remote;

import java.io.*;
import java.util.BitSet;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by douchengfeng on 2017/5/30.
 * 一个布隆过滤器，将url存在内存里，提升比较速率，是一个内存cache
 */
public class MemoryCache {
    private static final  int  DEFAULT_SIZE  = 204800 * 8 * 1024 ; //分200M的空间
    private static final  int [] seeds =new  int []{5,7, 11 , 13 , 31 , 37 , 61};
    private BitSet bits = new  BitSet(DEFAULT_SIZE);
    private  MyHash[] funcSet =new MyHash[seeds.length];
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);


    private MemoryCache(){
        //load();
        for(int x = 0; x < funcSet.length; x ++){
            funcSet[x] = new MyHash(seeds[x], DEFAULT_SIZE);
        }
    }

    public static MemoryCache getInstance(){
        return CacheHolder.instance;
    }

    /**
     * 用于录入url的指纹
     * @param url 未收录的url
     */
    void put(String url){
        readWriteLock.writeLock().lock();
        for(MyHash func: funcSet){
            bits.set(func.hash(url), true);
        }
        readWriteLock.writeLock().unlock();
    }

    boolean contains(String url){
        readWriteLock.readLock().lock();
        boolean result = true;
        for(MyHash func: funcSet){
            result = result && bits.get(func.hash(url));
        }
        readWriteLock.readLock().unlock();
        return result;
    }


    private class MyHash{
        private int seed;
        private int capacity;

        MyHash(int seed, int capacity){
            this.seed = seed;
            this.capacity =  capacity;
        }

        int hash(String value) {
            int  result=0 ;
            int  len= value.length();
            for  (int i= 0 ; i< len; i ++ ) {
                result =seed* result + value.charAt(i);
            }
            return (capacity - 1 ) & result;
        }
    }

    private static class CacheHolder {
        //由于instance是static final类型，保证了内存中只有这样一个实例存在，而且只能被赋值一次，从而保证了线程安全性
        private static final MemoryCache instance = new MemoryCache();
    }

    public void save(){
        File file = new File("src/filter.dat");
        try {
            FileOutputStream fos = new FileOutputStream(file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bits);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(){
        File file = new File("src/filter.dat");
        try{
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            bits = (BitSet) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            bits = new  BitSet(DEFAULT_SIZE);
            e.printStackTrace();
        }
    }



}
