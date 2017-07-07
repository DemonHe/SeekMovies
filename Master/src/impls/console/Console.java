package impls.console;

import impls.controller.MasterController;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by douchengfeng on 2017/6/2.
 * 控制台对象
 */
public class Console {
    private MasterController controller = new MasterController();
    private boolean stop = false;
    private int count = 0;

    public void start(){
        while(!stop){
            String input = readDataFromConsole();
            String[] command = input.split(" ");
            switch (command[0]){
                case "exit":
                    controller.closeAll();
                    stop = true;
                    RMIPortRelease();
                    break;
                case "add":
                    addMission(command[1], command[2]);
                    break;
                case "clear":
                    controller.clear();
                    break;
                case "restart":
                    this.restart(command);
                    break;
                default:
                    System.out.println(">>>>>INFO: 未知的命令！");
            }
        }
    }
    private void restart(String[] command){
        for(int i = 1; i < command.length; i ++){
            controller.restart(command[i]);
        }
        System.out.println(">>>>>重启成功!");
    }

    private String readDataFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String prompt = "donux@root#";
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private void addMission(String ip, String filePath){
        if(!isIP(ip)){
            System.out.println(">>>>>INFO:错误的IP格式！");
            return;
        }
        File file = new File(filePath);
        List<String> urls = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String url;
            while((url = reader.readLine()) != null){
                urls.add(url);
            }
            controller.beginNewMission(urls, "crawler" + count, ip + ":12000");
            count ++;
            System.out.println(">>>>>INFO:任务创建成功！");
        } catch (FileNotFoundException e) {
            System.out.println(">>>>>INFO:文件未找到！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isIP(String add)
    {
        if(add.length() < 7 || add.length() > 15 || "".equals(add))
        {
            return false;
        }
        if(add.equals("localhost")){
            return true;
        }
        /*
         * 判断IP格式和范围
         */
        String rex = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

        Pattern pat = Pattern.compile(rex);

        Matcher mat = pat.matcher(add);

        return mat.find();
    }

    private void RMIPortRelease() {
        //System.out.println("rmi port release");

//        System.out.println("Acquiring port... ");
//        Remote reg = null;
//        try {
//            reg = LocateRegistry.createRegistry(13000);
//        } catch (RemoteException ex) {
//            ex.printStackTrace();
//        }

        System.out.println("releasing port... ");
        try {
            Naming.unbind("rmi://localhost:13000/UrlDatabase");
            Naming.unbind("rmi://localhost:13000/Monitor");
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
