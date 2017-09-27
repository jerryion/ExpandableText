package com.crazypumpkin.library;

/**
 * Created by CrazyPumPkin on 16/8/2.
 */
public class Util {
    private static String[] nameArray = new String[]{
            "Windows", "Mac", "Linux"
    };

    private static String[] contentArray = new String[]{
            "Microsoft® Windows® 7/8/10 (32- or 64-bit)\n" +
                    "2 GB RAM minimum, 8 GB RAM recommended\n" +
                    "2 GB of available disk space minimum,\n" +
                    "4 GB Recommended (500 MB for IDE + 1.5 GB for Android SDK and emulator system image)\n" +
                    "1280 x 800 minimum screen resolution\n" +
                    "Java Development Kit (JDK) 8\n" +
                    "For accelerated emulator: 64-bit operating system and Intel® processor with support for Intel® VT-x, Intel® EM64T (Intel® 64), and Execute Disable (XD) Bit functionality" +
                    "2 GB RAM minimum, 8 GB RAM recommended\n" +
                    "2 GB of available disk space minimum,\n" +
                    "4 GB Recommended (500 MB for IDE + 1.5 GB for Android SDK and emulator system image)\n" +
                    "1280 x 800 minimum screen resolution\n" +
                    "Java Development Kit (JDK) 8\n" +
                    "For accelerated emulator: 64-bit operating system and Intel® processor with support for Intel® VT-x, Intel® EM64T (Intel® 64), and Execute Disable (XD) Bit functionality" +
                    "2 GB RAM minimum, 8 GB RAM recommended\n" +
                    "2 GB of available disk space minimum,\n" +
                    "4 GB Recommended (500 MB for IDE + 1.5 GB for Android SDK and emulator system image)\n" +
                    "1280 x 800 minimum screen resolution\n" +
                    "Java Development Kit (JDK) 8\n" +
                    "For accelerated emulator: 64-bit operating system and Intel® processor with support for Intel® VT-x, Intel® EM64T (Intel® 64), and Execute Disable (XD) Bit functionality",
            "Mac® OS X® 10.8.5 or higher, up to 10.11.4 (El Capitan)",
            "GNOME or KDE desktop\n" +
                    "Tested on Ubuntu® 12.04, Precise Pangolin (64-bit distribution capable of running 32-bit applications)\n" +
                    "64-bit distribution capable of running 32-bit applications\n" +
                    "GNU C Library (glibc) 2.11 or later\n" +
                    "2 GB RAM minimum, 8 GB RAM recommended\n" +
                    "2 GB of available disk space minimum,\n" +
                    "4 GB Recommended (500 MB for IDE + 1.5 GB for Android SDK and emulator system image)\n" +
                    "1280 x 800 minimum screen resolution\n" +
                    "Java Development Kit (JDK) 8\n" +
                    "For accelerated emulator: Intel® processor with support for Intel® VT-x, Intel® EM64T (Intel® 64), and Execute Disable (XD) Bit functionality, or AMD processor with support for AMD Virtualization™ (AMD-V™)"

    };

    public static String getContent(int position) {
        return contentArray[position % contentArray.length];
    }

    public static String getName(int position){
        return nameArray[position % nameArray.length];
    }
}
