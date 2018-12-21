// server side of messaging app
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// GUI same as client side
public class Server3 extends JFrame
{
    private static BufferedReader in;
    private PrintWriter out;
    private static JTextArea t1;
    private static JOptionPane d, d1;
    private static String name;
    private static Server3 server;
    private Color c = Color.BLACK;
    public Server3()
    {
        
        Object[] o = {"Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Purple", "Gray", "Black"};
        Checkbox cb = new Checkbox("Set bg clr", false);
        JPanel p1 = new JPanel();
        JPanel p3 = new JPanel();

        t1 = new JTextArea(3, 40);
        JTextField t3 = new JTextField(40);
        JTextField t4 = new JTextField(15);
        t1.setEditable(false);
        t1.setLineWrap(true);

        JScrollPane sp1 = new JScrollPane(t1);
        sp1.setPreferredSize(new Dimension(450, 130));

        Container main = getContentPane();
        main.setLayout(new FlowLayout());
        main.add(p1);
        main.add(p3);

        JButton b = new JButton("Clear");
        JButton b1 = new JButton("Color");
        
        p1.add(cb);
        p1.add(new JLabel("Chat"));
        p1.add(sp1);
        p3.add(new JLabel("Enter"));
        p3.add(b);
        p3.add(b1);
        p3.add(t3);
        d = new JOptionPane();
        d1 = new JOptionPane();
        t3.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e)
                {
                    String text = t3.getText();
                    t3.setText("");
                    t1.append( name + ": " + text + "\n");

                    out.println(name + ": " + text);

                }
            });
        b.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    t1.setText("");
                }
            });
        b1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int n = d1.showOptionDialog(server, "Set color of text", "Font", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE, null, o, o[8]);
                    if (n == 0)
                    {
                        c = Color.RED;
                        t1.setForeground(Color.RED);
                        t3.setForeground(Color.RED);
                    }
                    else if (n == 1)
                    {
                        c = Color.ORANGE;
                        t1.setForeground(Color.ORANGE);
                        t3.setForeground(Color.ORANGE);
                    }

                    else if (n == 2)
                    {
                        c = Color.YELLOW;
                        t1.setForeground(Color.YELLOW);
                        t3.setForeground(Color.YELLOW);
                    }
                    else if (n == 3)
                    {
                        c = Color.GREEN;
                        t1.setForeground(Color.GREEN);
                        t3.setForeground(Color.GREEN);
                    }
                    else if (n == 4)
                    {
                        c = Color.CYAN;
                        t1.setForeground(Color.CYAN);
                        t3.setForeground(Color.CYAN);
                    }
                    else if (n == 5)
                    {
                        c = Color.BLUE;
                        t1.setForeground(Color.BLUE);
                        t3.setForeground(Color.BLUE);
                    }
                    else if (n == 6)
                    {
                        c = Color.MAGENTA;
                        t1.setForeground(Color.MAGENTA);
                        t3.setForeground(Color.MAGENTA);
                    }
                    else if (n == 7)
                    {
                        c = Color.GRAY;
                        t1.setForeground(Color.GRAY);
                        t3.setForeground(Color.GRAY);
                    }
                    else 
                    {
                        c = Color.BLACK;
                        t1.setForeground(Color.BLACK);
                        t3.setForeground(Color.BLACK);
                    }
                }
            });
        cb.addItemListener(new ItemListener()
            {
                public void itemStateChanged(ItemEvent e)
                {
                    if (cb.getState())
                    {
                        main.setBackground(c);
                        p1.setBackground(c);
                        p3.setBackground(c);
                        cb.setBackground(c);
                    }
                    else
                    {
                        main.setBackground(null);
                        p1.setBackground(null);
                        p3.setBackground(null);  
                        cb.setBackground(null);
                    }

                }
            });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Server");

        setSize(650, 270);

        setVisible(true);

    }
    // set up server to accept clients
    public void connect() throws IOException
    {
        ServerSocket ss = new ServerSocket(42000);
        Socket s = ss.accept();
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new PrintWriter(s.getOutputStream(), true);

    }
    // initialize server and listen for client
    public static void main(String[] args)
    {
        
        server = new Server3();
        try
        {
            server.connect();
            t1.append("Connected\n");

        }
        catch (IOException e)
        {
            System.out.println("Something went wrong");

        }
        name = (String)d.showInputDialog(server, "Enter username", "Username", JOptionPane.PLAIN_MESSAGE);
        if (name == null)
        {
            name = "Server";
        }
        while (name.equals(""))
        {
            name = (String)d.showInputDialog(server, "Try again", "Username", JOptionPane.PLAIN_MESSAGE);
        }
        while (true){
            String response;
            try{
                response = in.readLine();

                t1.append(response + "\n");
            }
            catch(IOException er)
            {
                System.out.println("Something went wrong");
            }
        }
    }
}