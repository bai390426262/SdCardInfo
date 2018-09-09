package com.example.bhj;

import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvMemoryInfo = findViewById(R.id.tv_memory_info);

        //获取sd卡内存信息
        File sdcardDir = Environment.getExternalStorageDirectory();
        String sdcardMemory = getMemoryInfo(sdcardDir);

        //获取手机内部存储空间的信息
        File dataDirectory = Environment.getDataDirectory();
        String dataMemory = getMemoryInfo(dataDirectory);

        tvMemoryInfo.setText("SD卡:"+sdcardMemory+"\n手机内部:"+dataMemory);

    }


    /**
     * 根据路径获取内存信息
     * @param path
     * @return
     */
    private String getMemoryInfo(File path){

        //获得一个磁盘的状态对象
        StatFs stat = new StatFs(path.getPath());

        long blockSizeLong = stat.getBlockSizeLong();//获得一个扇区的大小

        long blockCountLong = stat.getBlockCountLong();//获得扇区的总数

        long availableBlocksLong = stat.getAvailableBlocksLong();//获得可用扇区的个数

        //总空间
        String tatalMemory = Formatter.formatFileSize(this, blockSizeLong * blockCountLong);

        //可用空间
        String availableMemory = Formatter.formatFileSize(this, blockSizeLong * availableBlocksLong);

        return "总空间:"+tatalMemory+"\n可用空间:"+availableMemory;
    }
}
