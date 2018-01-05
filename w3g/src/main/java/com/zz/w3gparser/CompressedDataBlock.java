package com.zz.w3gparser;

import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * Created by dell on 2018/1/5.
 */
public class CompressedDataBlock {
    private int compressedDataSize;

    private int uncompressedDataSize;

    private byte[] uncompressedDataBytes;

    public int getCompressedDataSize() {
        return compressedDataSize;
    }

    public int getUncompressedDataSize() {
        return uncompressedDataSize;
    }

    public byte[] getUncompressedDataBytes() {
        return uncompressedDataBytes;
    }

    public CompressedDataBlock(byte[] fileBytes, int offset) throws DataFormatException, W3GException {
        System.out.println("开始处理一个压缩数据块...");

        //压缩数据大小
        compressedDataSize = LittleEndianTool.getUnsignedInt16(fileBytes, offset);
        System.out.println("1-2字节：" + compressedDataSize);

        //解压缩数据大小
        uncompressedDataSize = LittleEndianTool.getUnsignedInt16(fileBytes, offset + 2);
        System.out.println("3-4字节：" + uncompressedDataSize);

        // 压缩数据，从第8个字节开始，长度为compressedDataSize，解压缩
        uncompressedDataBytes = new byte[uncompressedDataSize];
        Inflater inflater = new Inflater();
        inflater.setInput(fileBytes, offset + 8, compressedDataSize);
        int realUncompressedDataSize = inflater.inflate(uncompressedDataBytes);
        if (realUncompressedDataSize != uncompressedDataSize) {
            throw new W3GException("解压缩数据异常");
        }

    }


}
