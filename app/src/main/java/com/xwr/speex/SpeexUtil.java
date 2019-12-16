package com.xwr.speex;

/**
 * Create by xwr on 2019/12/16
 * Describe:
 */
public class SpeexUtil {
  private static final int DEFAULT_COMPRESSION = 8;
  private static final SpeexUtil speex = new SpeexUtil();

  private SpeexUtil() {

  }

  public static SpeexUtil getInstance() {
    return speex;
  }

  public void init() {
    load();
    open(DEFAULT_COMPRESSION);
    initEcho(160,320);//80,160,320
  }

  private void load() {
    try {
      System.loadLibrary("libspeex");
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  public native int open(int compression);

  public native int getFrameSize();
  /**
   * Decode
   * @param encoded input
   * @param lin output
   * @param size output size
   */
  public native int decode(byte encoded[], short lin[], int size);
  /**
   * Eecode
   * @param lin input
   * @param offset input start location
   * @param encoded output
   * @param size input lin buffer size
   */
  public native int encode(short lin[], int offset, byte encoded[], int size);

  public native void close();


  public native void initEcho(int frameSize, int filterLength);

  public native void echoCancellation(short[] rec, short[] play, short[] out);

  public native int echoCancellationEncode(short[] rec, short[] play, byte[] encoded);

  public native void destroyEcho();

  public native int getAecStatus();
}
