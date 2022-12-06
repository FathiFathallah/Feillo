package jbuf;
import java.nio.*;
import java.util.*;

public class JBuf {

    public static void main(String[] args) {
/*
        //tryByteBuffers();// Uncomment this to play and understand ByteBuffers
        System.out.println("<<<<<<<<<<<<<<<< Packet <<<<<<<<<<<<<<<<<<<<<");
        Packet packet = new Packet( 255 , 0);
        packet.addData(ba, ba.length);
        packet.printPacket();
        
        
        System.out.println("<<<<<<<<<<<<<< Printed As Buffer <<<<<<<<<<<<<<<<<<<<<<<");
        ByteBuffer byteBuf = packet.toByteBuffer();//byteBuf is just a reference also packet.buffer
        
        packet.printPacketAsArray();
        //to send using the Datagram packet .. use see slide 112 in chapter 2 Version 5
        // Fill it as follows
        byte[] sendData = byteBuf.array();
        int length = packet.getPacketLength();
        // DatagramPacket sendPacket =  new DatagramPacket(sendData,length, IPAddress,port); 
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+length);

        // Suppose we received a packet in recevData, here I will just copy the data from the packet
        //  byte rcvData     get it from the DatagramPacket sendPacket ...you get itfrom the rcv code..
        // I will just copy it to show that it works
        byte[] recvData = new byte[length]; // I will just copy what I sent
        /* for(int i =0; i < length; i++){  // you can also use System.arrayCopy
             recvData[i] = sendData[i];
         }*/
        /*
        System.arraycopy(sendData, 0, recvData, 0, length); // you can also use System.arraycopy
        
        ByteBuffer rcvBuf = ByteBuffer.wrap(recvData);
        // Here you have received it, to decode it use
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+length);
        Packet rcvPacket = new Packet(0,0);
        rcvPacket.extractPacketfromByteBuffer(rcvBuf);
        System.out.println("\nReceived Packet ===>");
        rcvPacket.printPacket(); // should be simlar to the packet we created 

        System.out.println("<<<<<<<<<<<<<<<End Of Packet <<<<<<<<<<<<<<<<<<<<<<");
        /////////////////////////////////*/
        
        /*Packet packet11 = new Packet(0);
        byte[] FileName = "THIS IS FILE NAME HAHAHAHAHAHA".getBytes();
        packet11.addData(FileName, FileName.length);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+packet11.getPacketLength());
*/
    }

    static void tryByteBuffers() {
        System.out.println("==========  Byte Buffer Start ================");
        // Declare and initialize the byte array
        byte[] bb = {10, 20, 30};

        // wrap the byte array into ByteBuffer
        // using wrap() method
        ByteBuffer byteBuffer = ByteBuffer.wrap(bb);

        // Typecast ByteBuffer to Buffer
        Buffer buffer = (Buffer) byteBuffer;
        System.out.println("Buffer before--: "
                + Arrays.toString((byte[]) buffer.array())
                + "\nPosition: " + buffer.position()
                + "\nLimit: " + buffer.limit());
        // set position at index 1
        buffer.position(1);

        // print the buffer
        System.out.println("Buffer before flip: "
                + Arrays.toString((byte[]) buffer.array())
                + "\nPosition: " + buffer.position()
                + "\nLimit: " + buffer.limit());

        // Flip the Buffer
        // using flip() method
        buffer.flip();

        // print the buffer
        System.out.println("\nBuffer after flip: "
                + Arrays.toString((byte[]) buffer.array())
                + "\nPosition: " + buffer.position()
                + "\nLimit: " + buffer.limit());
        ////////////////////////////

        ByteBuffer b1 = ByteBuffer.allocate(20);
        b1.putChar('A');

        printBuffer(b1);

        b1.clear();
        b1.putChar('B'); // 2 bytes;
        int iv = 0x3456789A;
        b1.putInt(iv);//4 bytes
        System.out.println(iv);
        b1.putFloat((float) 22.56);//4 bytes
        byte[] ba = {(byte) 0xf1, (byte) 0xA1, (byte) 0x34, (byte) 0x33, (byte) 0x55};//5bytes
        System.out.println(ba.length);
        b1.putInt(ba.length);
        b1.put(ba);//offset =
        printBuffer(b1);
        printBufferHex(b1);
        // Now Extract the Data
        extractData(b1);
        //b1.clear();// clear just sets the position to 0
        //printBufferHex(b1);
        System.out.println("==========  Byte Buffer End ================");
    }

    // needed for  tryByteBuffers only
    static void printBuffer(ByteBuffer b) {
        System.out.println("\n b1 "
                + Arrays.toString((byte[]) b.array())
                + "\nPosition: " + b.position()
                + "\nLimit: " + b.limit());

    }
    // needed for  tryByteBuffers only

    static void printBufferHex(ByteBuffer b) {
        int limit = b.limit();
        String S = "[";
        for (int i = 0; i < limit; i++) {
            S += String.format("%02X", b.array()[i] & 0x00ff);
            if (i != (limit - 1)) {
                S += ", ";
            }
        }
        S += "]\n";
        S += "Position: " + b.position()
                + "\nLimit: " + b.limit();
        System.out.println(S);
    }

    // needed for  tryByteBuffers only
    static void extractData(ByteBuffer b) {
        try {
            // b1.putChar('B'); // 2 bytes;
            // b1.putInt(0x3456789A);//4 bytes
            // b1.putFloat((float)22.56);//4 bytes
            // byte[] ba = {(byte)0xf1,(byte)0xA1,(byte)0x34,(byte)0x33,(byte)0x55};//5bytes
            char cV = b.getChar(0); // index 0, 2 bytes
            int iV = b.getInt(0 + 2 + 4);// stored at index 2
            float fV = b.getFloat(0 + 2 + 4);//offset 6
            int baLength = b.getInt(0 + 2 + 4 + 4);
            System.out.println(">> BaLength:= " + baLength);
            byte[] ba = new byte[baLength];

            // b.get(ba,10,1);//we know there are 5 bytes.
            for (int i = 0; i < 5; i++) {
                byte bt = (byte) b.get(0 + 2 + 4 + 4 + 4 + i);
                ba[i] = bt;
            }

            System.out.println("Char cV:" + cV);
            System.out.println("int iV:" + iV);
            System.out.println("float fV:" + fV);
            System.out.println("int  ba.lenth:" + baLength);

            String S = "";
            int limit = ba.length;
            for (int i = 0; i < limit; i++) {
                S += String.format("%02X", (byte) ba[i] & 0x00ff);
                if (i != (limit - 1)) {
                    S += ", ";
                }
            }
            System.out.println(S);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // =========================================================
}
