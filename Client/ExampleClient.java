
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.rmi.*;
import java.rmi.server.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;  
  
import java.beans.PropertyChangeListener;  
import java.beans.PropertyChangeEvent;  
import javax.swing.JButton;



import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;  


public class ExampleClient extends JFrame {
    // public static final String bi = null;  
    // private static JPanel contentPane;  
    private static JFrame f;
    private static JTextField jtf;
    // private static Jbutton b;

    public String img_path;
    public String GUImsg;
    public static void main(String[] args) {
        
        try {
            // EventQueue.invokeLater(new Runnable() {  
            //     public void run() {  
            //         try {  
            //             String num = JOptionPane.showInputDialog("show sth");
            //             // frame.setVisible(true);  
            //         } catch (Exception e) {  
            //             e.printStackTrace();  
            //         }  
            //     }  
            // }); 
            // String num = JOptionPane.showInputDialog("show sth");
            
            ImageProcessor ip = (ImageProcessor) Naming.lookup("rmi://localhost:10001/create");
            f = runJF(ip);
            f.setVisible(true);
            // SerializableImage si_created = createSerializableImage("Berry.JPG");
            // //Error handling: not valid file
            // if(si_created == null) System.out.println("not valid file");
            // //Error handling: send serialzable image failed
            // String msg = ip.sendSerializableImage(si_created);
            // System.out.println(msg);
            // SerializableImage si_recv = ip.recvSerializableImage();
            // try{
            //     processReceivedSerializableImage(si_recv);
            //     System.out.println("process successful");
            // }catch(Exception e){
            //     System.out.println("process failed");
            // }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static SerializableImage createSerializableImage(String str){
        File f = null;
        try{
            URL path = ExampleClient.class.getResource(str);
            f = new File(path.getFile());
            BufferedImage img = ImageIO.read(f);
            SerializableImage si = new SerializableImage();
            si.setImage(img);
            System.out.println("success");
            return si;

        }catch (Exception e){
            //Error handling: Cannot transform to SI
            return null;
        }
    }
    //right now for testing
    public static BufferedImage processReceivedSerializableImage(SerializableImage bi) throws Exception{

        BufferedImage bi_recv = bi.getImage();
        return bi_recv;
        // File output = new File("D://Img//OutputBerryClient.jpg");
        // ImageIO.write(bi_recv, "jpg", output);
    }

    public static JFrame runJF(ImageProcessor ip){
        JFrame jf = new JFrame("Example Client");
        jf.setSize(100000,100000);
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        // JButton button_select = new JButton("select file");
        JButton button_send = new JButton("send file");
        JButton button_retrieve = new JButton("retrieve file");
        button_send.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String file_name = jtf.getText();
                System.out.println(file_name);
                SerializableImage si_created = createSerializableImage(file_name);

                if(si_created != null){
                    try{
                        String msg_server = ip.sendSerializableImage(si_created);
                        System.out.println(msg_server);
                    }catch(Exception ex){
                        System.out.println("cannot send");
                    }
                    
                }else{
                    System.out.println("error send");
                }
            }  
        }); 
        button_retrieve.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) { 
                try{
                    SerializableImage si_recv = ip.recvSerializableImage();
                    BufferedImage bi_recv = processReceivedSerializableImage(si_recv);
                    JPanel jp_img = new JPanel();
                    jp_img.setSize(bi_recv.getWidth(), bi_recv.getHeight());
                    jp_img.paint(bi_recv.getGraphics());
                    f.add(jp_img);
                    System.out.println("process successful");
                } catch(Exception ex){
                    System.out.println("process failed");
                }
            }  
        });  
        jtf = new JTextField("");  
        jtf.setPreferredSize(new Dimension(100,30));
        p.add(jtf);
        p.add(button_send);
        p.add(button_retrieve);
        
        jf.add(p);
        return jf;
    }




    // protected boolean do_button_actionPerformed(ActionEvent e) throws IOException {  
    //     // int option = fileChooser.showOpenDialog(this);
    //     // if (option == JFileChooser.APPROVE_OPTION) {  
    //     //     File file = fileChooser.getSelectedFile();  
    //     //     imageLabel.setImageFile(file);  
    //     //    // getExtension(file);
            
    //     // }  
    //     return true;
    // }
    //GUI methods
    // public Input Input() {  
    //     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    //     setBounds(100, 100, 629, 428);  
    //     contentPane = new JPanel();  
    //     contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));  
    //     contentPane.setLayout(new BorderLayout(0, 0));  
    //     setContentPane(contentPane);  
          
    //     JPanel panel = new JPanel();  
    //     contentPane.add(panel, BorderLayout.NORTH);    
    //     JButton chooseButton = new JButton("Choose Picture");  
    //     // chooseButton.addActionListener(new ActionListener() {  
    //     //     public void actionPerformed(ActionEvent e) {  
    //     //         try {
	// 	// 			do_button_actionPerformed(e);
	// 	// 		} catch (IOException e1) {
	// 	// 			e1.printStackTrace();
	// 	// 		}  
    //     //     }  
    //     // });  
    //     panel.add(chooseButton);

    //     JButton okButton = new JButton("OK");
    //     // okButton.addActionListener(new ActionListener() {  
    //     //     public void actionPerformed(ActionEvent e) {  
    //     //         try {
	// 	// 			ok_button_actionPerformed(e);
	// 	// 		} catch (IOException e1) {
	// 	// 			e1.printStackTrace();
	// 	// 		}  
    //     //     }  
    //     // });
    //     // panel.add(okButton);
      
        
          
    //     // imageLabel = new ImagePreviewer((JFileChooser) null);  
    //     // contentPane.add(imageLabel, BorderLayout.CENTER);  
    //     // initFileChooser();  
    // // }  
    
    
    
   
//     private void initFileChooser() {  
//         fileChooser = new JFileChooser();
//         previewer = new ImagePreviewer(fileChooser);
//         fileChooser.setCurrentDirectory(new File("C:\\Img")); 
//         fileChooser.setFileFilter(new FileNameExtensionFilter("jpg-,png-Datei", "jpg", "png"));  
//         fileChooser.addPropertyChangeListener("SelectedFileChangedProperty",  
//                 new PropertyChangeListener() {  
//                     public void propertyChange(PropertyChangeEvent evt) {  
//                         previewer.setImageFile((File) evt.getNewValue());  
//                     }  
//                 });  
//         fileChooser.setAccessory(previewer);  
//     }  


    
 
//     private void read(File file) {
//     	try {
// 			BufferedImage bi = ImageIO.read(file);
// 		} catch (IOException e) {
// 			e.printStackTrace();
// 		}
//     }
//     public static class MyRemoteObject{
//     	public final String ImageProcessorImpl = null;

// 		public static void read(File file) throws IOException {
//     			BufferedImage bi = ImageIO.read(file);
//     			if (bi == null){
//     				JOptionPane.showMessageDialog(null, "Bitte einen Bild auswählen! ", "Fehler",JOptionPane.WARNING_MESSAGE); 
//     			    return;
//     			}else {
//     				createSerializableImage("Berry.JPG");
//     			}	
//     	}

		
//     }
}




  



// class ImagePreviewer extends JLabel {  
//     public ImagePreviewer(JFileChooser chooser) {  

//         setPreferredSize(new Dimension(200, 200));  
//         setHorizontalAlignment(JLabel.CENTER);
//         setBorder(new LineBorder(Color.GRAY));
//         setOpaque(true);
//         setBackground(Color.WHITE);
//         setText("No picture set");
//     }  
      

//     public void setImageFile(File file) {  
//         setText("");
//         if (file == null) { 
//             return;
//         }  
 
//         ImageIcon icon = new ImageIcon(file.getPath());  
//         if (icon.getIconWidth() > getWidth()) {  
//             icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(),  
//                     -1, Image.SCALE_DEFAULT));  
//         }  
//         setIcon(icon);  
//         repaint();  
//     }  
// }  