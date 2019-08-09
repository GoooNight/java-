package resourcesDownload;

import java.io.File;

public class VideoSpeed implements Runnable{
    File file ;

    public VideoSpeed(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        while (VideoURL.flag == 0) {
            try {
                System.out.println(file.length() / 1024f / 1024f + "MB");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
