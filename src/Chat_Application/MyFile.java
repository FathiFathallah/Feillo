/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Chat_Application;

/**
 *
 * @author pc
 */
public class MyFile {

    private int ID;
    private String Name;
    private byte[] Data;
    private String FileExtension;
    private int FileSize;

    public MyFile(int ID , String Name ,String FileExtension , byte[] Data , int FileSize) {
        this.ID = ID;
        this.Name = Name;
        this.FileExtension = FileExtension;
        this.Data = Data;
        this.FileSize = FileSize;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }

    public void setData(byte[] Data) {
        this.Data = Data;
    }

    public void setFileExtension(String fileExtension) {
        this.FileExtension = fileExtension;
    }
    
    public int getID() {
        return this.ID;
    }
    
    public String getName() {
        return this.Name;
    }

    public byte[] getData() {
        return this.Data;
    }

    public String getFileExtension() {
        return this.FileExtension;
    }
    
    public int getFileFileSize() {
        return this.FileSize;
    }    
}
