import java.sql.*;
import javax.swing.JOptionPane;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.wb.swt.SWTResourceManager;

public class Start {

	protected Shell shlDigitalHarmony;
	private Text txtemail;
	private Text txtname;
	private Text txtpword1;
	private Text txtpass;
	private Text txtuser;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Start window = new Start();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlDigitalHarmony.open();
		shlDigitalHarmony.layout();
		while (!shlDigitalHarmony.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDigitalHarmony = new Shell();
		shlDigitalHarmony.setImage(SWTResourceManager.getImage(Start.class, "/javax/swing/plaf/metal/icons/Question.gif"));
		shlDigitalHarmony.setSize(450, 300);
		shlDigitalHarmony.setText("Digital Harmony");
		
		Label lblEmail = new Label(shlDigitalHarmony, SWT.NONE);
		lblEmail.setBounds(10, 39, 55, 15);
		lblEmail.setText("Email");
		
		txtemail = new Text(shlDigitalHarmony, SWT.BORDER);
		txtemail.setBounds(102, 39, 76, 21);
		
		txtname = new Text(shlDigitalHarmony, SWT.BORDER);
		txtname.setBounds(102, 85, 76, 21);
		
		txtpword1 = new Text(shlDigitalHarmony, SWT.BORDER);
		txtpword1.setBounds(102, 176, 76, 21);
		
		Label lblNewLabel = new Label(shlDigitalHarmony, SWT.NONE);
		lblNewLabel.setBounds(10, 88, 75, 15);
		lblNewLabel.setText("User Name");
		
		Label lblNewLabel_1 = new Label(shlDigitalHarmony, SWT.NONE);
		lblNewLabel_1.setBounds(10, 179, 55, 15);
		lblNewLabel_1.setText("Password");
		
		Button btnNewButton = new Button(shlDigitalHarmony, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","rootpassword");
					//Statement s=con.createStatement();
					//s.executeUpdate("INSERT INTO 'usertesting'(username,email,password) VALUES ('"+txtname.getText()+"','"+txtemail.getText()+"','"+txtpword1.getText()+"')");
					PreparedStatement pstmt = con.prepareStatement("INSERT INTO `usertesting` (username,email,password) VALUE (?,?,?)");
					pstmt.setString(1, txtname.getText() );
					pstmt.setString(2, txtemail.getText());
					pstmt.setString(3, txtpword1.getText());
					pstmt.executeUpdate();
					
							con.close();
		
								
				} catch(Exception e1) {System.out.print(e1);}
				
			}
		});
		btnNewButton.setBounds(89, 226, 88, 25);
		btnNewButton.setText("Create Account");
		
		DateTime dateTime = new DateTime(shlDigitalHarmony, SWT.BORDER);
		dateTime.setBounds(98, 125, 80, 24);
		
		Label lblDateOfBirth = new Label(shlDigitalHarmony, SWT.NONE);
		lblDateOfBirth.setBounds(10, 125, 75, 15);
		lblDateOfBirth.setText("Date of birth");
		
		Label lblNewLabel_2 = new Label(shlDigitalHarmony, SWT.NONE);
		lblNewLabel_2.setBounds(243, 110, 55, 15);
		lblNewLabel_2.setText("Password");
		
		txtpass = new Text(shlDigitalHarmony, SWT.BORDER);
		txtpass.setBounds(318, 110, 76, 21);
		
		Label lblNewLabel_4 = new Label(shlDigitalHarmony, SWT.NONE);
		lblNewLabel_4.setBounds(243, 69, 63, 15);
		lblNewLabel_4.setText("User Name");
		
		Button btnNewButton_1 = new Button(shlDigitalHarmony, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","rootpassword");
					Statement stmt=con.createStatement();
					String sql="Select * from usertesting where username='"+txtuser.getText()+"' and password='"+txtpass.getText().toString()+"'";
							ResultSet rs=stmt.executeQuery(sql);
							if(rs.next())
								//System.out.print("yay");
								JOptionPane.showMessageDialog(null, "login successful");
							else
								//System.out.print("nah");
								JOptionPane.showMessageDialog(null, "incorrect");
							con.close();
		
								
				} catch(Exception e1) {System.out.print(e1);}
				
			}
		});
		btnNewButton_1.setBounds(293, 226, 75, 25);
		btnNewButton_1.setText("Sign In");
		
		txtuser = new Text(shlDigitalHarmony, SWT.BORDER);
		txtuser.setBounds(318, 66, 76, 21);
		
		Label lblNewLabel_3 = new Label(shlDigitalHarmony, SWT.NONE);
		lblNewLabel_3.setImage(SWTResourceManager.getImage("C:\\Users\\nigel\\Desktop\\digital harmony.JPG"));
		lblNewLabel_3.setBounds(0, 0, 434, 261);

	}
}
