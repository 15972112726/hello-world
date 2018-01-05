package com.zz.w3gparser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by dell on 2018/1/5.
 */
public class Replay {
    private Header header;

    private UncompressedData uncompressedData;

    public Replay(File w3gFile) throws IOException, W3GException, DataFormatException {

        byte[] fileBytes = fileToByteArray(w3gFile);
        header = new Header(fileBytes);

        // 遍历解析每个压缩数据块，解压缩，合并
        long compressedDataBlockCount = header.getCompressedDataBlockCount();
        byte[] uncompressedDataBytes = new byte[0]; // 所有压缩数据块中数据解压合并到这个数组中
        int offset = 68;
        for (int i = 0; i < compressedDataBlockCount; i++) {
            CompressedDataBlock compressedDataBlock = new CompressedDataBlock(fileBytes, offset);

            // 数组合并
            byte[] blockUncompressedData = compressedDataBlock.getUncompressedDataBytes();
            byte[] temp = new byte[uncompressedDataBytes.length + blockUncompressedData.length];
            System.arraycopy(uncompressedDataBytes, 0, temp, 0, uncompressedDataBytes.length);
            System.arraycopy(blockUncompressedData, 0, temp, uncompressedDataBytes.length, blockUncompressedData.length);
            uncompressedDataBytes = temp;

            int blockCompressedDataSize = compressedDataBlock.getCompressedDataSize();
            offset += 8 + blockCompressedDataSize;
        }

        // 压缩数据块解压合并后结果就是字节数组uncompressedDataBytes
        uncompressedData = new UncompressedData(uncompressedDataBytes);
    }

    /**
     * 将文件转换成字节数组
     *
     * @param w3gFile 文件
     * @return 字节数组
     * @throws IOException
     */
    private byte[] fileToByteArray(File w3gFile) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(w3gFile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int n;

        try {
            while ((n = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, n);
            }
        } finally {
            fileInputStream.close();
        }

        return byteArrayOutputStream.toByteArray();
    }

    public Header getHeader() {
        return header;
    }

    public UncompressedData getUncompressedData() {
        return uncompressedData;
    }
}
