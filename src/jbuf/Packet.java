package jbuf;
import java.nio.*;


public class Packet {

    static final int MAX_DATA_LENGTH = 1024;
    private byte seqControl;// 1 Byte
    private byte seqNumber;// 1 Byte
    private byte LastPacket;//1 Byte
    private int dataLength;// 4 Bytes
    private int fileSize;// 4 Bytes
    private int Checksum;// 4 Bytes (you can just add the Bytes in the packet and store the result in Checksum)
    private byte[] data;
    private ByteBuffer buffer;
    static final int HEADER_LENGTH = (int) (1 + 1 + 1 + 4 + 4 + 4);

    public Packet() {
        this.seqControl = 0;
        this.seqNumber = 0;
        this.LastPacket = 0;
        this.dataLength = 0;
        this.fileSize = 0;
        this.Checksum = 0;
        this.data = new byte[ MAX_DATA_LENGTH ];
        this.buffer = null;
    }
    public byte getSeqControl() {
        return seqControl;
    }

    public void setSeqControl(byte seqControl) {
        this.seqControl = seqControl;
    }

    public byte getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(byte seqNumber) {
        this.seqNumber = seqNumber;
    }

    public int getChecksum() {
        return Checksum;
    }
    
    public void setChecksum() {
        this.Checksum = this.seqControl + this.seqNumber + this.LastPacket + this.dataLength + this.fileSize;
    }
    
    public void set_Generated_Checksum(int generatedChecksum) {
        this.Checksum = generatedChecksum;
    }
    
    public byte getLastPacket() {
        return LastPacket;
    }

    public void setLastPacket(byte LastPacket) {
        this.LastPacket = LastPacket;
    }
    

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public byte[] getData() {
        return data;
    }
    
    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
    
    public boolean addData(byte[] DATAa, int n) {
        this.dataLength = n;
        if (n > MAX_DATA_LENGTH) {
            System.out.println("Data too big");
            return false;
        }
        System.arraycopy(DATAa , 0 , this.data , 0 , n);
        return true;
    }

    public ByteBuffer toByteBuffer() {
        buffer = ByteBuffer.allocate(HEADER_LENGTH + MAX_DATA_LENGTH);
        buffer.clear();
        buffer.put(seqControl);//index 0
        buffer.put(seqNumber);//index 1
        buffer.put(LastPacket);//index 2
        buffer.putInt(dataLength);//4 bytes | 3 => 6 | INDEX 3
        buffer.putInt(fileSize);//4 bytes | 7 => 10 | INDEX 7
        buffer.putInt(Checksum); // 4 Bytes | 11 => 14 | INDEX 11
        buffer.put(this.data, 0, this.dataLength);//copy ony the vailable bytes
        return buffer;
    }

    public int getPacketLength() {
        return HEADER_LENGTH + dataLength;
    }

    public void extractPacketfromByteBuffer(ByteBuffer buf) {
        try {
            byte seqControl = buf.get(0);
            byte seqNumber = buf.get(1); 
            byte LastPacket = buf.get(2); 
            int dataLen = buf.getInt(3);
            int fileSize = buf.getInt(7);
            int Checksum = buf.getInt(11);
            byte[] data = new byte[dataLen];
            for (int i = 0; i < dataLen; i++) {
                byte bt = (byte) buf.get(1 + 1 + 1 + 4 + 4 + 4 + i);
                data[i] = bt;
            }
            this.seqControl = seqControl;
            this.seqNumber = seqNumber;
            this.LastPacket = LastPacket;
            this.dataLength = dataLen;
            this.fileSize = fileSize;
            this.Checksum = Checksum;
            this.data = data;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
