package Chat_Application;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
 
/**
 *
 * @author pc
 */
public class TCPServer extends javax.swing.JFrame {

    /**
     * Creates new form TCPServer
     */
    private int TCP_Port;
    private InetAddress TCP_IPAddress;
    private final DefaultListModel USERSLIST;
    private ServerSocket welcomeSocket;
    private boolean isListening = false;
    private ReceivingConnections waitingConnections;
    ArrayList<String> Online_Users;
    ArrayList<Socket> SOCKETS;
    private InputStreamReader Reader;
    private BufferedReader inFromClient;
    private PrintWriter output;
        
    public TCPServer() {
        initComponents();
        try {
            this.TCP_IPAddress = InetAddress.getLocalHost();
            this.AvailableInterfaces.addItem(this.TCP_IPAddress.getHostName() +": " + this.TCP_IPAddress.getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientChat_App.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.OnlineUsersList.setForeground(new Color(0,153,51));
        this.USERSLIST = new DefaultListModel();
        this.OnlineUsersList.setModel(USERSLIST);
        Online_Users = new ArrayList<String>();
        SOCKETS = new ArrayList<Socket>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StartListening = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        PORT = new javax.swing.JTextField();
        AvailableInterfaces = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        OnlineUsersList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        status = new javax.swing.JTextField();
        LoginIndication = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TCP Server");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        StartListening.setText("Srart Listening");
        StartListening.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartListeningActionPerformed(evt);
            }
        });

        jLabel1.setText("Port:");

        OnlineUsersList.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        OnlineUsersList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(OnlineUsersList);

        jScrollPane2.setViewportView(jTextPane1);

        jLabel2.setText("Online Users");

        jLabel3.setText("Status:");

        status.setEditable(false);

        LoginIndication.setBackground(java.awt.Color.red);
        LoginIndication.setForeground(new java.awt.Color(179, 1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(AvailableInterfaces, 0, 262, Short.MAX_VALUE)
                                .addComponent(jScrollPane2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(StartListening)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PORT, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(LoginIndication)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(127, 127, 127))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LoginIndication, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StartListening, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PORT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(3, 3, 3)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AvailableInterfaces, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //CHECK IF THE STRING IS NUMBER    
	
    private void StartListeningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartListeningActionPerformed
       
       if(!this.PORT.getText().isEmpty()){
           if(!this.isListening){
        try {
            this.isListening = true;
            String tokens[] = this.AvailableInterfaces.getSelectedItem().toString().split(": ");
            this.TCP_IPAddress = InetAddress.getByName(tokens[1]);
            this.TCP_Port = Integer.parseInt(this.PORT.getText());
            this.welcomeSocket = new ServerSocket(this.TCP_Port);
            this.status.setForeground(new Color(0,153,51));
            this.status.setText("Server Name: "+TCP_IPAddress.getHostName()+" , Server IP Address: "+TCP_IPAddress.getHostAddress()+ " , Server Port Number: "+this.TCP_Port);
            this.StartListening.setForeground(new Color(0,153,51));
            this.StartListening.setBackground(new Color(0,153,51));
            this.LoginIndication.setBackground(Color.green);
            this.LoginIndication.setForeground(new Color(0,153,51));
              
            this.waitingConnections = new ReceivingConnections();
            //Now We have the TCP Server Set Up And Ready to be contacted
                    
        }
        catch (UnknownHostException ex) { Logger.getLogger(ClientChat_App.class.getName()).log(Level.SEVERE, null, ex); } 
        catch (IOException | NumberFormatException ex) { Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex); } 
    }
           else JOptionPane.showMessageDialog(TCPServer.this,"SERVER IS ALREADY LISTENING","Starting TCP Server Faild!", JOptionPane.ERROR_MESSAGE);
    }   
        else{ JOptionPane.showMessageDialog(TCPServer.this,"Port Number Needed!","Starting TCP Server Faild!", JOptionPane.ERROR_MESSAGE); }    
    }//GEN-LAST:event_StartListeningActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try {
            this.welcomeSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TCPServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TCPServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TCPServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TCPServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TCPServer().setVisible(true);
            }
        });
    }

    
class ReceivingConnections extends Thread {
    Socket connectionSocket;
    int OnlineUsersIndex = 0;
    int TCPSocketsIndex = 0;
    String userInfo;
    PrintWriter outToClient;
    public ReceivingConnections() {
        start();
    }
    //We need every connection socket so we will add every socket to a Linked List
    @Override
    public void run() {
            while (true) {
                try {
                    connectionSocket = welcomeSocket.accept();
                    inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    output = new PrintWriter(connectionSocket.getOutputStream(), true);
                    userInfo = inFromClient.readLine();
                    
                    if(userInfo.charAt(0) == 'A'){
                        SOCKETS.add(TCPSocketsIndex,connectionSocket);
                        TCPSocketsIndex++;
                        Online_Users.add(OnlineUsersIndex,userInfo.substring(1));
                        OnlineUsersIndex++;
                        USERSLIST.addElement(userInfo.substring(1));
                        appendWithColor(jTextPane1 ,userInfo.substring(1)+" Has Connected\n" , new Color(0,153,51));
                        sendOnlineUsers();
                    }
                    else if(userInfo.charAt(0) == 'D'){
                        USERSLIST.removeElement(userInfo.substring(1));
                        appendWithColor(jTextPane1 ,userInfo.substring(1)+" Has Disconnected\n" , new Color(179,1,30));
                        deleteOnlineUsers(userInfo.substring(1));
                    }              
                } 
                catch (IOException ex) { Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex); } 
                catch (Exception ex) { Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex); }
            } 
    }

        private void sendOnlineUsers() throws IOException {
            
            for(int i = 0 ; i<SOCKETS.size() ; i++ ){
                outToClient = new PrintWriter(SOCKETS.get(i).getOutputStream(), true);
                for(int j = 0; j<Online_Users.size() ; j++){
                    outToClient.println(Online_Users.get(j));
                }
            }
            
        }
        
        private void deleteOnlineUsers(String userToDelete) throws IOException{
            int indexForDelete = Online_Users.indexOf(userToDelete);
            Online_Users.remove(indexForDelete);
            OnlineUsersIndex--;
            SOCKETS.remove(indexForDelete);
            TCPSocketsIndex--;
            for(int i=0 ; i<SOCKETS.size() ; i++){
            outToClient = new PrintWriter(SOCKETS.get(i).getOutputStream(), true);
            outToClient.println("D" + userToDelete);
            }
        }        
    }

            //this function allows us to add text into the JTextPane and also with changing the color 
        //so we can add a line of text with specefic color that we want
        private void appendWithColor(JTextPane tp, String msg, Color c){
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Times New Roman");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> AvailableInterfaces;
    private javax.swing.JSeparator LoginIndication;
    private javax.swing.JList<String> OnlineUsersList;
    private javax.swing.JTextField PORT;
    private javax.swing.JButton StartListening;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextField status;
    // End of variables declaration//GEN-END:variables
}
