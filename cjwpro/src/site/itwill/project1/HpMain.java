package site.itwill.project1;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;;

public class HpMain extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	JTextField noTF, nameTF, phoneTF, addressTF, birthTF, docnoTF;
	JButton addB, deleteB, updateB, searchB, cancelB;
	JButton addnB, deletenB, updatenB, searchnB, searchnB2;

	JTable table;

	int cmd;

	addWindow aw;
	deleteWindow dw;
	searchNoWindow snw;
	updateWindow uw;
	searchWindow sw;

	public HpMain() throws Exception {

		setTitle("ȯ�� ��Ʈ");
		setSize(800, 400);

		Dimension dim = getToolkit().getScreenSize();
		setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);

//		�Է�â

//		���� Ŭ��â
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1, 5));

		bottom.add(addB = new JButton("�Կ�"));
		addB.addActionListener(this);
		bottom.add(deleteB = new JButton("���"));
		deleteB.addActionListener(this);
		bottom.add(updateB = new JButton("����"));
		updateB.addActionListener(this);
		bottom.add(searchB = new JButton("�˻�"));
		searchB.addActionListener(this);
		bottom.add(cancelB = new JButton("���"));
		cancelB.addActionListener(this);

		Object[] title = { "��ȣ", "�̸�", "��ȭ��ȣ", "�ּ�", "�������", "����ǻ�" };
		table = new JTable(new DefaultTableModel(title, 0));
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		JScrollPane sp = new JScrollPane(table);

		add(sp, "Center");
		add(bottom, "South");
		displayAllPa();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) throws Exception {

		new HpMain();
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Component c = (Component) ev.getSource();
		try {
			if (c == addB) {
				aw = new addWindow();
			}
			if (c == deleteB) {
				dw = new deleteWindow();
			}
			if (c == updateB) {
				snw = new searchNoWindow();
			}
			if (c == searchB) {
				sw = new searchWindow();
			}
			if (c == cancelB) {
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("���� �߻� : " + e.getLocalizedMessage());
		}
	}

	public boolean addPa() {

		String noTemp = noTF.getText();
		if (noTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "ȯ�ڹ�ȣ�� �ݵ�� �Է��� �ּ���.");
			noTF.requestFocus();// �Է������� �̵��ϴ� �޼ҵ�
			return false;
		}

		String noReg = "\\d{4}";
		if (!Pattern.matches(noReg, noTemp)) {
			JOptionPane.showMessageDialog(this, "ȯ�ڹ�ȣ�� 4�ڸ� �����Դϴ�.");
			noTF.requestFocus();
			return false;
		}

		int no = Integer.parseInt(noTemp);

		if (HpDAO.getDAO().selectNoPatient(no) != null) {
			JOptionPane.showMessageDialog(this, "�̹� ������� ��ȣ�Դϴ�.");
			noTF.requestFocus();
			return false;
		}

		String name = nameTF.getText();

		if (name.equals("")) {
			JOptionPane.showMessageDialog(this, "�̸��� �ݵ�� �Է��� �ּ���.");
			nameTF.requestFocus();
			return false;
		}

		String nameReg = "[��-�R]{2,5}";
		if (!Pattern.matches(nameReg, name)) {
			JOptionPane.showMessageDialog(this, "�̸��� 2~5 ������ �ѱ۸� �Է��� �ּ���.");
			nameTF.requestFocus();
			return false;
		}

		String phone = phoneTF.getText();

		if (phone.equals("")) {
			JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� �ݵ�� �Է��� �ּ���.");
			phoneTF.requestFocus();
			return false;
		}

		String phoneReg = "(01[016789])-\\d{3,4}-\\d{4}";
		if (!Pattern.matches(phoneReg, phone)) {
			JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� ���Ŀ� �°� �Է��� �ּ���.");
			phoneTF.requestFocus();
			return false;
		}

		String address = addressTF.getText();

		if (address.equals("")) {
			JOptionPane.showMessageDialog(this, "�ּҸ� �ݵ�� �Է��� �ּ���.");
			addressTF.requestFocus();
			return false;
		}

		String birth = birthTF.getText();

		if (birth.equals("")) {
			JOptionPane.showMessageDialog(this, "��������� �ݵ�� �Է��� �ּ���.");
			birthTF.requestFocus();
			return false;
		}

		String birthdayReg = "(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]||3[01])";
		if (!Pattern.matches(birthdayReg, birth)) {
			JOptionPane.showMessageDialog(this, "��������� ���Ŀ� �°� �Է��� �ּ���.");
			birthTF.requestFocus();
			return false;
		}

		String noTemp2 = docnoTF.getText();
		if (noTemp2.equals("")) {
			JOptionPane.showMessageDialog(this, "�ǻ��ڵ带 �ݵ�� �Է��� �ּ���.");
			docnoTF.requestFocus();// �Է������� �̵��ϴ� �޼ҵ�
			return false;
		}

		String noReg2 = "\\d{2}";
		if (!Pattern.matches(noReg2, noTemp2)) {
			JOptionPane.showMessageDialog(this, "�ǻ��ڵ�� 2�ڸ� �����Դϴ�.");
			docnoTF.requestFocus();
			return false;
		}

		int docno = Integer.parseInt(noTemp2);

		HpDTO hp = new HpDTO();
		hp.setNo(no);
		hp.setName(name);
		hp.setPhone(phone);
		hp.setAddress(address);
		hp.setBirth(birth);
		hp.setDocno(docno);
		int rows = HpDAO.getDAO().insertPatient(hp);

		JOptionPane.showMessageDialog(this, rows + "���� ȯ�������� ���� �Ͽ����ϴ�.");

		displayAllPa();
		return true;
	}

	public void displayAllPa() {
		List<HpDTO> hpList = HpDAO.getDAO().selectAllPatientList();

		if (hpList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "����� ȯ�������� �����ϴ�.");
			return;
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (int i = model.getRowCount(); i > 0; i--) {

			model.removeRow(0);
		}

		for (HpDTO hp : hpList) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(hp.getNo());
			rowData.add(hp.getName());
			rowData.add(hp.getPhone());
			rowData.add(hp.getAddress());
			rowData.add(hp.getBirth());
			rowData.add(hp.getDocno());
			model.addRow(rowData);
		}
	}

	public boolean removePa() {
		String noTemp = noTF.getText();
		if (noTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "ȯ�ڹ�ȣ�� �ݵ�� �Է��� �ּ���.");
			noTF.requestFocus();
			return false;
		}

		String noReg = "\\d{4}";
		if (!Pattern.matches(noReg, noTemp)) {
			JOptionPane.showMessageDialog(this, "ȯ�ڹ�ȣ�� 4�ڸ� �����Դϴ�.");
			noTF.requestFocus();
			return false;
		}

		int no = Integer.parseInt(noTemp);

		int rows = HpDAO.getDAO().deletePatient(no);

		if (rows > 0) {
			JOptionPane.showMessageDialog(this, rows + "���� ȯ�ڸ� ���� �Ͽ����ϴ�.");
			displayAllPa();
		}

		return true;

	}

	public boolean searchNoPa() {
		String noTemp = noTF.getText();
		if (noTemp.equals("")) {
			JOptionPane.showMessageDialog(this, "ȯ�ڹ�ȣ�� �ݵ�� �Է��� �ּ���.");
			noTF.requestFocus();
			return false;
		}

		String noReg = "\\d{4}";
		if (!Pattern.matches(noReg, noTemp)) {
			JOptionPane.showMessageDialog(this, "ȯ�ڹ�ȣ�� 4�ڸ� �����Դϴ�.");
			noTF.requestFocus();
			return false;
		}

		int no = Integer.parseInt(noTemp);

		HpDTO hp = HpDAO.getDAO().selectNoPatient(no);
		if (hp == null) {
			JOptionPane.showMessageDialog(this, "�����ϰ��� �ϴ� ȯ�������� �����ϴ�.");
			noTF.requestFocus();
			noTF.setText("");
			return false;
		}

		return true;

	}

	public boolean modifyPa() {
		int no = Integer.parseInt(noTF.getText());

		String name = nameTF.getText();

		if (name.equals("")) {
			JOptionPane.showMessageDialog(this, "�̸��� �ݵ�� �Է��� �ּ���.");
			nameTF.requestFocus();
			return false;
		}

		String nameReg = "[��-�R]{2,5}";
		if (!Pattern.matches(nameReg, name)) {
			JOptionPane.showMessageDialog(this, "�̸��� 2~5 ������ �ѱ۸� �Է��� �ּ���.");
			nameTF.requestFocus();
			return false;
		}

		String phone = phoneTF.getText();

		if (phone.equals("")) {
			JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� �ݵ�� �Է��� �ּ���.");
			phoneTF.requestFocus();
			return false;
		}

		String phoneReg = "(01[016789])-\\d{3,4}-\\d{4}";
		if (!Pattern.matches(phoneReg, phone)) {
			JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� ���Ŀ� �°� �Է��� �ּ���.");
			phoneTF.requestFocus();
			return false;
		}

		String address = addressTF.getText();

		if (address.equals("")) {
			JOptionPane.showMessageDialog(this, "�ּҸ� �ݵ�� �Է��� �ּ���.");
			addressTF.requestFocus();
			return false;
		}

		String birth = birthTF.getText();

		if (birth.equals("")) {
			JOptionPane.showMessageDialog(this, "��������� �ݵ�� �Է��� �ּ���.");
			birthTF.requestFocus();
			return false;
		}

		String birthdayReg = "(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]||3[01])";
		if (!Pattern.matches(birthdayReg, birth)) {
			JOptionPane.showMessageDialog(this, "��������� ���Ŀ� �°� �Է��� �ּ���.");
			birthTF.requestFocus();
			return false;
		}

		String noTemp2 = docnoTF.getText();
		if (noTemp2.equals("")) {
			JOptionPane.showMessageDialog(this, "�ǻ��ڵ带 �ݵ�� �Է��� �ּ���.");
			docnoTF.requestFocus();
			return false;
		}

		String noReg2 = "\\d{2}";
		if (!Pattern.matches(noReg2, noTemp2)) {
			JOptionPane.showMessageDialog(this, "�ǻ��ڵ�� 2�ڸ� �����Դϴ�.");
			docnoTF.requestFocus();
			return false;
		}

		int docno = Integer.parseInt(noTemp2);

		HpDTO hp = new HpDTO();
		hp.setNo(no);
		hp.setName(name);
		hp.setPhone(phone);
		hp.setAddress(address);
		hp.setBirth(birth);
		hp.setDocno(docno);

		int rows = HpDAO.getDAO().updatePatient(hp);

		JOptionPane.showMessageDialog(this, rows + "���� ȯ�������� ���� �Ͽ����ϴ�.");

		displayAllPa();
		return true;
	}

	public boolean searchNamePa() {
		String name = nameTF.getText();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(this, "�̸��� �ݵ�� �Է��� �ּ���.");
			nameTF.requestFocus();
			return false;
		}

		String nameReg = "[��-�R]{2,5}";
		if (!Pattern.matches(nameReg, name)) {
			JOptionPane.showMessageDialog(this, "�̸��� 2~5 ������ �ѱ۸� �Է��� �ּ���.");
			nameTF.requestFocus();
			return false;
		}

		List<HpDTO> hpList = HpDAO.getDAO().selectNamePatientList(name);

		if (hpList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "�˻��� ȯ�������� �����ϴ�.");
			return false;
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(0);
		}

		for (HpDTO hp : hpList) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(hp.getNo());
			rowData.add(hp.getName());
			rowData.add(hp.getPhone());
			rowData.add(hp.getAddress());
			rowData.add(hp.getBirth());
			rowData.add(hp.getDocno());
			model.addRow(rowData);
		}

		return true;
	}
//	�Կ�, ���, �˻�, ���� �� ���ο� â ����
	class addWindow extends JFrame implements ActionListener {
		private static final long serialVersionUID = 1L;

		addWindow() {
			setTitle("ȯ�� �߰�");

			JPanel NewWindow = new JPanel();
			setContentPane(NewWindow);

			JPanel c = new JPanel();
			c.setLayout(new GridLayout(8, 1));

			JPanel pno = new JPanel();
			pno.add(new JLabel("��  ȣ"));
			pno.add(noTF = new JTextField(10));

			JPanel pname = new JPanel();
			pname.add(new JLabel("��  ��"));
			pname.add(nameTF = new JTextField(10));

			JPanel pbirthday = new JPanel();
			pbirthday.add(new JLabel("��  ��"));
			pbirthday.add(birthTF = new JTextField(10));

			JPanel pphone = new JPanel();
			pphone.add(new JLabel("��  ȭ"));
			pphone.add(phoneTF = new JTextField(10));

			JPanel paddress = new JPanel();
			paddress.add(new JLabel("��  ��"));
			paddress.add(addressTF = new JTextField(10));

			JPanel pdocno = new JPanel();
			pno.add(new JLabel("��  ��"));
			pno.add(docnoTF = new JTextField(10));

			c.add(addnB = new JButton("Ȯ  ��"));
			addnB.addActionListener(this);

			c.add(pno);
			c.add(pname);
			c.add(pphone);
			c.add(paddress);
			c.add(pbirthday);
			c.add(pdocno);
			c.add(addnB);
			add(c, "Center");

			setSize(500, 400);
			setResizable(false);
			setVisible(true);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Component c = (Component) e.getSource();
			if (c == addnB) {
				if (addPa() == true) {
					aw.dispose();
				}

			}

		}

	}

	class deleteWindow extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;

		deleteWindow() {
			setTitle("ȯ�� ����");

			JPanel NewWindow = new JPanel();
			setContentPane(NewWindow);

			JPanel c = new JPanel();
			c.setLayout(new GridLayout(2, 1));

			JPanel pno = new JPanel();
			pno.add(new JLabel("��  ȣ"));
			pno.add(noTF = new JTextField(10));

			c.add(deletenB = new JButton("Ȯ  ��"));
			deletenB.addActionListener(this);
			c.add(pno);
			c.add(deletenB);
			add(c, "Center");

			setSize(500, 400);
			setResizable(false);
			setVisible(true);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Component c = (Component) e.getSource();
			if (c == deletenB) {
				if (removePa() == true) {
					dw.dispose();
				}
			}

		}
	}

	class searchNoWindow extends JFrame implements ActionListener {
		private static final long serialVersionUID = 1L;

		searchNoWindow() {
			setTitle("������ ȯ�� ��ȣ �Է�");

			JPanel NewWindow = new JPanel();
			setContentPane(NewWindow);

			JPanel c = new JPanel();
			c.setLayout(new GridLayout(2, 1));

			JPanel pno = new JPanel();
			pno.add(new JLabel("��  ȣ"));
			pno.add(noTF = new JTextField(10));

			c.add(searchnB = new JButton("Ȯ  ��"));
			searchnB.addActionListener(this);
			c.add(pno);
			c.add(searchnB);
			add(c, "Center");

			setSize(500, 400);
			setResizable(false);
			setVisible(true);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Component c = (Component) e.getSource();
			if (c == searchnB) {
				if (searchNoPa() == true) {
					snw.dispose();
					uw = new updateWindow();
				}
			}
		}

	}

	class updateWindow extends JFrame implements ActionListener {
		private static final long serialVersionUID = 1L;

		updateWindow() {
			setTitle("���� ����");

			JPanel NewWindow = new JPanel();
			setContentPane(NewWindow);

			JPanel c = new JPanel();
			c.setLayout(new GridLayout(7, 1));

			JPanel pname = new JPanel();
			pname.add(new JLabel("��  ��"));
			pname.add(nameTF = new JTextField(10));

			JPanel pbirthday = new JPanel();
			pbirthday.add(new JLabel("��  ��"));
			pbirthday.add(birthTF = new JTextField(10));

			JPanel pphone = new JPanel();
			pphone.add(new JLabel("��  ȭ"));
			pphone.add(phoneTF = new JTextField(10));

			JPanel paddress = new JPanel();
			paddress.add(new JLabel("��  ��"));
			paddress.add(addressTF = new JTextField(10));

			JPanel pdocno = new JPanel();
			pdocno.add(new JLabel("��  ��"));
			pdocno.add(docnoTF = new JTextField(10));

			c.add(updatenB = new JButton("Ȯ  ��"));
			updatenB.addActionListener(this);

			c.add(pname);
			c.add(pphone);
			c.add(paddress);
			c.add(pbirthday);
			c.add(pdocno);
			c.add(updatenB);
			add(c, "Center");

			setSize(500, 400);
			setResizable(false);
			setVisible(true);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Component c = (Component) e.getSource();
			if (c == updatenB) {
				if (modifyPa() == true) {
					uw.dispose();
				}
			}

		}

	}

	class searchWindow extends JFrame implements ActionListener {
		private static final long serialVersionUID = 1L;

		searchWindow() {

			setTitle("�˻��� ȯ�� �̸� �Է�");

			JPanel NewWindow = new JPanel();
			setContentPane(NewWindow);

			JPanel c = new JPanel();
			c.setLayout(new GridLayout(2, 1));

			JPanel pname = new JPanel();
			pname.add(new JLabel("��  ��"));
			pname.add(nameTF = new JTextField(10));

			c.add(searchnB2 = new JButton("Ȯ  ��"));
			searchnB2.addActionListener(this);
			c.add(pname);
			c.add(searchnB2);
			add(c, "Center");

			setSize(500, 400);
			setResizable(false);
			setVisible(true);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Component c = (Component) e.getSource();
			if (c == searchnB2) {
				if (searchNamePa() == true) {
					sw.dispose();
				}
			}

		}

	}

}