import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.JTextField;
import javax.swing.JButton;
public class FTP_pair extends JPanel
{
JTabbedPane tabbedPane;
JComponent clientPane;
JComponent serverPane;
public FTP_pair()
{
super(new GridLayout(1, 1));
tabbedPane=new JTabbedPane();
clientPane=makeClientPane();
serverPane=makeServerPane();
tabbedPane.add("Client",clientPane);
tabbedPane.add("Server",serverPane);
add(tabbedPane);
tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
}
 protected JComponent makeClientPane() 
 {
 JPanel panel = new JPanel();
 final JFileChooser chooser=new JFileChooser();
 JLabel filename=new JLabel("Filename");
 JLabel port=new JLabel("Port");
 JLabel server=new JLabel("Server");
 JLabel dummy_1=new JLabel();
 JLabel dummy_2=new JLabel();
 JLabel dummy_3=new JLabel();
 JLabel dummy_4=new JLabel();
 final JTextField filepath=new JTextField();
 final JTextField port_text=new JTextField();
 final JTextField server_ip=new JTextField();
 JButton browse=new JButton("Browse");
 JButton send=new JButton("Send");
 send.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	System.out.println("Before start sending");
	try
	{
	Client.sendfile(server_ip.getText(),Integer.parseInt(port_text.getText()),filepath.getText());
	}
	catch(Exception e)
	{
	System.out.println(e.getMessage());
	}
	System.out.println("Finishes Sending");
	}
	});
  browse.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
		chooser.setFileHidingEnabled(false);
      int returnVal = chooser.showOpenDialog(null);
      if(returnVal == JFileChooser.APPROVE_OPTION) {
	 String path=chooser.getSelectedFile().getAbsolutePath();
	  filepath.setText(path);
	  }
	}
});
panel.setLayout(new GridLayout(4,3));
panel.add(filename);
panel.add(filepath);
panel.add(browse);
panel.add(port);
panel.add(port_text);
panel.add(dummy_1);
panel.add(server);
panel.add(server_ip);
panel.add(dummy_2);
panel.add(send);
panel.add(dummy_3);
panel.add(dummy_4);
return panel;
}
 protected JComponent makeServerPane() 
 {
 JPanel panel = new JPanel();
 panel.setLayout(new GridLayout(4,2));
 JLabel folder=new JLabel("Folder");
 JLabel filename=new JLabel("Filename");
 JLabel port=new JLabel("Port");
JButton start=new JButton("Start"); 
final JLabel dummy=new JLabel();
 final JTextField folder_path=new JTextField();
 final JTextField filename_text=new JTextField();
 final JTextField port_text=new JTextField();
 panel.add(folder);
 panel.add(folder_path);
 panel.add(filename);
 panel.add(filename_text);
 panel.add(port);
 panel.add(port_text);
 panel.add(start);
 panel.add(dummy);
 start.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	String fullpath=folder_path.getText()+filename_text.getText();
	System.out.println(folder_path.getText());
	System.out.println(filename_text.getText());
	dummy.setText("Server Starting");
	try
	{
	Server.startServer(Integer.parseInt(port_text.getText()),fullpath);
	}
	catch(Exception e)
	{
	e.getMessage();
	}
	dummy.setText("Server Ended");
	}
	});
 return panel;
 }
private static void createAndShowGUI() {
//Create and set up the window.
JFrame frame = new JFrame("FTP_Pair");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//Add content to the window.
frame.add(new FTP_pair(), BorderLayout.CENTER);
frame.pack();
frame.setVisible(true);
}
public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });
    } 
 
}