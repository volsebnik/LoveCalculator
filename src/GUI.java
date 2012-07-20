
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JTextField;

public class GUI {

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;
	private JMenu fileMenu = null;
	private JMenu helpMenu = null;
	private JMenuItem exitMenuItem = null;
	private JMenuItem aboutMenuItem = null;
	private JDialog aboutDialog = null;  //  @jve:decl-index=0:visual-constraint="421,44"
	private JPanel aboutContentPane = null;
	private JLabel aboutVersionLabel = null;
	private JButton dugme = null;
	private JLabel prvoImeLabel = null;
	private JLabel drugoImeLabel = null;
	private JTextField prvoIme = null;
	private JTextField drugoIme = null;
	private JLabel naziv = null;

	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setSize(400, 320);
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("Love Calculator");
		}
		return jFrame;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			kompatibilnostLabel = new JLabel();
			kompatibilnostLabel.setBounds(new Rectangle(175, 168, 46, 36));
			kompatibilnostLabel.setText("");
			naziv = new JLabel();
			naziv.setBounds(new Rectangle(86, 29, 193, 51));
			naziv.setFont(new Font("Dialog", Font.BOLD, 24));
			naziv.setHorizontalAlignment(SwingConstants.CENTER);
			naziv.setHorizontalTextPosition(SwingConstants.CENTER);
			naziv.setOpaque(true);
			naziv.setText("Love calculator");
			drugoImeLabel = new JLabel();
			drugoImeLabel.setBounds(new Rectangle(264, 124, 93, 27));
			drugoImeLabel.setText("Partner's name");
			prvoImeLabel = new JLabel();
			prvoImeLabel.setBounds(new Rectangle(25, 120, 92, 31));
			prvoImeLabel.setText("Your name");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getDugme(), null);
			jContentPane.add(prvoImeLabel, null);
			jContentPane.add(drugoImeLabel, null);
			jContentPane.add(getPrvoIme(), null);
			jContentPane.add(getDrugoIme(), null);
			jContentPane.add(naziv, null);
			jContentPane.add(kompatibilnostLabel, null);
			jContentPane.add(getIzlaz(), null);
		}
		return jContentPane;
	}

	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}


	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog aboutDialog = getAboutDialog();
					aboutDialog.pack();
					Point loc = getJFrame().getLocation();
					loc.translate(20, 20);
					aboutDialog.setLocation(loc);
					aboutDialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	private JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(getJFrame(), true);
			aboutDialog.setTitle("About");
			aboutDialog.setContentPane(getAboutContentPane());
		}
		return aboutDialog;
	}

	private JPanel getAboutContentPane() {
		if (aboutContentPane == null) {
			aboutContentPane = new JPanel();
			aboutContentPane.setLayout(new BorderLayout());
			aboutContentPane.add(getAboutVersionLabel(), BorderLayout.CENTER);
		}
		return aboutContentPane;
	}

	private JLabel getAboutVersionLabel() {
		if (aboutVersionLabel == null) {
			aboutVersionLabel = new JLabel();
			aboutVersionLabel.setText("Version 1.0 powered by Milos");
			aboutVersionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return aboutVersionLabel;
	}
	
	static int m = 0;
	
	private JButton getDugme() {
		if (dugme == null) {
			dugme = new JButton();
			dugme.setBounds(new Rectangle(168, 98, 59, 50));
			dugme.setFont(new Font("Dialog", Font.BOLD, 18));
			dugme.setText("?");
			dugme.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (m==0)pocetakPrograma();
					m++;
					if (!proveraPolja())return;
						
					int i = proveriElement(prvoIme.getText(), drugoIme.getText());
					if (i == -1){
						int k = izracunajKompatibilnost();
						kompatibilnostLabel.setText(k+"");
						unesiUListu(new Par(prvoIme.getText(), drugoIme.getText(), k));
					}	
						else
						kompatibilnostLabel.setText(parovi.get(i).getKompatibilnost()+"");
					
					
				}
			});
		}
		return dugme;
	}

	private JTextField getPrvoIme() {
		if (prvoIme == null) {
			prvoIme = new JTextField();
			prvoIme.setBounds(new Rectangle(27, 182, 83, 20));
		}
		return prvoIme;
	}

	private JTextField getDrugoIme() {
		if (drugoIme == null) {
			drugoIme = new JTextField();
			drugoIme.setBounds(new Rectangle(269, 183, 82, 20));
		}
		return drugoIme;
	}
	
	static LinkedList<Par> parovi = new LinkedList<Par>();  //  @jve:decl-index=0:
	private JLabel kompatibilnostLabel = null;
	private JButton izlaz = null;
	
	public int proveriElement(String ime1, String ime2){
		for(int i=0; i<parovi.size(); i++){
			
		if(parovi.get(i).getIme1().equalsIgnoreCase(ime1) 
				&& parovi.get(i).getIme2().equalsIgnoreCase(ime2))
					return i;
		if(parovi.get(i).getIme1().equalsIgnoreCase(ime2) 
				&& parovi.get(i).getIme2().equalsIgnoreCase(ime1))
					return i;
		}
		return -1;
	}
	
/*	public void ispisiListu(LinkedList<Par> parovi){
		
		for(int i=0; i<parovi.size(); i++){
        	String s1 = parovi.get(i).getIme1();
        	String s2 = parovi.get(i).getIme2();
        	String s3 = parovi.get(i).getKompatibilnost() + ""; 
        	System.out.println(s1 + " " + s2 + " " + s3);
            }
		
	}*/
	
	public void pocetakPrograma(){
	//	parovi.clear();
		 parovi = unesiIzFajla();
		
	}
	
	public void izListeUFajl(){
		
		try{
		PrintWriter pw = new PrintWriter( new FileWriter("ListaParova.txt"));
		for(int i=0; i<parovi.size(); i++){
        	String s1 = parovi.get(i).getIme1();
        	String s2 = parovi.get(i).getIme2();
        	String s3 = parovi.get(i).getKompatibilnost() + ""; 
        	pw.println(s1 + " " + s2 + " " + s3 + "\n");
            }
		pw.close();		
		}catch(Exception e)
		{System.out.println("greska "+ e.getMessage());}
		
	}
	
    public LinkedList<Par> unesiIzFajla(){
    	LinkedList<Par> parovi = new LinkedList<Par>();
        try{

        BufferedReader br = new BufferedReader(new InputStreamReader(
        		new DataInputStream(new FileInputStream("ListaParova.txt"))));
        String pom;
        while ((pom = br.readLine()) != null)   {
        	pom = br.readLine();
            String[] s = pom.split(" ");
            parovi.add((new Par(s[0], s[1], Integer.parseInt(s[2]))));
        }
        br.close();
        
          }catch (Exception e){
        	  System.err.println("Error: " + e.getMessage());
        }
          return parovi;
}

    public void unesiUListu(Par p){
    	parovi.add(p);
    }
	
    public int izracunajKompatibilnost(){
    	Random r = new Random();
    	return r.nextInt(99)+1;
    }
    
	public boolean proveraPolja(){
		if(prvoIme.getText().isEmpty() || drugoIme.getText().isEmpty()){ 
			JOptionPane.showMessageDialog(jFrame, "Please enter your names");
			return false;
		}
		return true;
		
	}


	private JButton getIzlaz() {
		if (izlaz == null) {
			izlaz = new JButton();
			izlaz.setBounds(new Rectangle(168, 225, 60, 21));
			izlaz.setText("EXIT");
			izlaz.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					izListeUFajl();
					System.exit(0);
				}
			});
		}
		return izlaz;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI application = new GUI();
				application.getJFrame().setVisible(true);
			}
		});
	}

}
