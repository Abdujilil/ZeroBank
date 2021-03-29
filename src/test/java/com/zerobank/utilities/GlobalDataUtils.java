package com.zerobank.utilities;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GlobalDataUtils {

    @Getter @Setter
    private static String downloadPath;
    public static int numberOfFilesDownloaded;

}
