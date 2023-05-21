package Hotel;

import lib.XFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HotelManApp extends JFrame{
    private JTable tbManage;
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtPhone;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton exitButton;
    private JTextField txtCheckIn;
    private JTextField txtCheckOut;
    private JComboBox comboBoxFloor;
    private JPanel mainPanel;
    private JComboBox comboBoxRoom;
    private JTextField txtRoom;
    private JButton refreshButton;
    private JButton OKButton;
    private JButton sortByNameButton;

    //custom instance variable

    DefaultTableModel tableModel;
    DefaultComboBoxModel comboBoxModelF= new DefaultComboBoxModel<>();
    DefaultComboBoxModel comboBoxModelR= new DefaultComboBoxModel<>();
    ArrayList<HotelMan> hotelManArrayList;
    String filePath = "Hotel.dat";
    int currentRow = -1;

    public HotelManApp(String title){
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        //2. First load Combo Box, Table
        initTable();
        loadCb();
        tbManage.setDefaultEditor(Object.class,null);

        //3. Load data file (in project)
        hotelManArrayList = new ArrayList<>();
        boolean file = loadFile();
        if(file){
            fillToTable();
        }else {
            showMess("Nothing");
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHotel();
            }
        });


        tbManage.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentRow = tbManage.getSelectedRow();
                showDetail(currentRow);

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateIn4();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteList();
            }
        });
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomCode();
            }
        });

        sortByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortByName();fillToTable();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int re = JOptionPane.showConfirmDialog(HotelManApp.this,"Are you sure?", "Exit?",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if (re == JOptionPane.YES_NO_OPTION){
                    System.exit(0);
                }
            }
        });



    }
    private void sortByName() {
        Collections.sort(hotelManArrayList, new Comparator<HotelMan>() {
           @Override
            public int compare(HotelMan o1, HotelMan o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    private void roomCode() {
        String floor = comboBoxFloor.getSelectedItem().toString();
        String room = comboBoxRoom.getSelectedItem().toString();
        txtRoom.setText(floor+room);

    }
    private void deleteList() {
        //1. update candidate to arraylist with currentRow
        deleteHotel();

        //2. fillToTable(renewTB)
        fillToTable();
        //3. save arraylist candidate
        saveFile();
    }
    private void deleteHotel() {
        int re = JOptionPane.showConfirmDialog(this,""+
                        "Do you want to delete this one?","Delete Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (re == JOptionPane.YES_NO_OPTION) {
            hotelManArrayList.remove(currentRow);
            resetForm();
        }
    }
    private void resetForm() {
        txtID.setText("");
        txtName.setText("");
        txtPhone.setText("");
        txtRoom.setText("");
        txtCheckIn.setText("");
        txtCheckOut.setText("");
        comboBoxRoom.setSelectedIndex(0);
        comboBoxRoom.setSelectedIndex(0);

    }
    private void updateIn4() {
        //1. update candidate to arraylist with currentRow
        updateHotel();

        //2. fillToTable(renewTB)
        fillToTable();
        //3. save arraylist candidate
        saveFile();
    }
    private void updateHotel() {
        HotelMan h = hotelManArrayList.get(currentRow);
        String id = txtID.getText();
        h.setID(id);

        String name=txtName.getText();
        h.setName(name);

        String phone = txtPhone.getText();
        h.setPhone(phone);

        String room = txtRoom.getText();
        h.setRoom(room);

        String checkIn = txtCheckIn.getText();
        h.setCheckIn(checkIn);

        String checkOut = txtCheckOut.getText();
        h.setCheckOut(checkOut);

        Boolean gen=femaleRadioButton.isSelected();
        h.setGender(gen);

    }
    private void showDetail(int currentRow) {
        HotelMan h = hotelManArrayList.get(currentRow);
        String id = h.getID();
        txtID.setText(id);

        String name = h.getName();
        txtName.setText(name);

        String phone = h.getPhone();
        txtPhone.setText(phone);

        String room = h.getRoom();
        txtRoom.setText(room);

        comboBoxFloor.setSelectedItem(room.substring(0,1));
        comboBoxRoom.setSelectedItem(room.substring(1));

        Boolean gen=h.getGender();
        femaleRadioButton.setSelected(gen);
        maleRadioButton.setSelected(!gen);

        String checkIn = h.getCheckIn();
        txtCheckIn.setText(checkIn);

        String checkOut = h.getCheckOut();
        txtCheckOut.setText(checkOut);

    }
    private void addHotel() {
        //1. add new candidate to arraylist
        addToList();
        //2. fillToTable
        fillToTable();
        //3. save arraylist candidate
        saveFile();

    }
    private void saveFile() {
        XFile.writeObject(filePath,hotelManArrayList);
    }
    private void addToList() {
        String id = txtID.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();
        Boolean gender = femaleRadioButton.isSelected();
        String room = txtRoom.getText();
        String checkIn = txtCheckIn.getText();
        String checkOut = txtCheckOut.getText();
        HotelMan h = new HotelMan(id, name, phone,gender,room,checkIn,checkOut);
        hotelManArrayList.add(h);
    }
    private void showMess(String mess) {
        JOptionPane.showMessageDialog(this,mess);
    }
    private void fillToTable() {
        //clear old data in the table
        tableModel.setRowCount(0);
        for (HotelMan h: hotelManArrayList){
            Object[] row = new Object[]{
                    h.getID(),h.getName(),h.getPhone(),h.getGender(),h.getRoom(),h.getCheckIn(),h.getCheckOut()
            };
            tableModel.addRow(row);
        }
    }
    private boolean loadFile() {
        if(XFile.readObject(filePath)==null){
            return false;
        }
        hotelManArrayList=(ArrayList<HotelMan>)XFile.readObject(filePath);
        return true;
    }
    private void loadCb() {
        String[] floorList = {"Choose the floor","A","B"};
        String[] roomList = {"Choose the room","01","02","03","04","05"};
        for(String f:floorList){
            comboBoxModelF.addElement(f);
        }
        for(String r:roomList){
            comboBoxModelR.addElement(r);
        }
        comboBoxFloor.setModel(comboBoxModelF);
        comboBoxRoom.setModel(comboBoxModelR);
    }
    private void initTable() {
        String[] colNames = {"ID","Name","Phone","Gender","Room","CheckIn","CheckOut"};
        tableModel= new DefaultTableModel(colNames,0);
        tbManage.setModel(tableModel);

    }
    public static void main(String[] args) {
        HotelManApp h = new HotelManApp("acdd");
        h.setVisible(true);
        h.setLocationRelativeTo(null);
    }
}
