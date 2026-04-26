package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class KasirFrame extends JFrame{

JLabel barangLabel,qtyLabel,totalLabel,bayarLabel,kembaliLabel;

JTextField txtBarang,txtHarga,txtQty;
JTextField txtTotal,txtBayar,txtKembali;

JButton btnTambah,btnBayar;

JTable table;
DefaultTableModel model;

int totalBelanja=0;

public KasirFrame(){

setTitle("Aplikasi Kasir");
setSize(700,500);
setLayout(null);

barangLabel=
new JLabel("Barang");
barangLabel.setBounds(30,30,100,30);
add(barangLabel);

txtBarang=
new JTextField();
txtBarang.setBounds(120,30,150,30);
add(txtBarang);


JLabel hargaLabel=
new JLabel("Harga");
hargaLabel.setBounds(30,70,100,30);
add(hargaLabel);

txtHarga=
new JTextField();
txtHarga.setBounds(120,70,150,30);
add(txtHarga);


qtyLabel=
new JLabel("Qty");
qtyLabel.setBounds(30,110,100,30);
add(qtyLabel);

txtQty=
new JTextField();
txtQty.setBounds(120,110,150,30);
add(txtQty);


btnTambah=
new JButton("Tambah");
btnTambah.setBounds(300,70,120,30);
add(btnTambah);


String kolom[]={
"Barang",
"Harga",
"Qty",
"Subtotal"
};

model=
new DefaultTableModel(kolom,0);

table=
new JTable(model);

JScrollPane sp=
new JScrollPane(table);

sp.setBounds(30,170,600,150);
add(sp);



totalLabel=
new JLabel("Total");
totalLabel.setBounds(30,340,100,30);
add(totalLabel);

txtTotal=
new JTextField();
txtTotal.setBounds(120,340,150,30);
add(txtTotal);


bayarLabel=
new JLabel("Bayar");
bayarLabel.setBounds(30,380,100,30);
add(bayarLabel);

txtBayar=
new JTextField();
txtBayar.setBounds(120,380,150,30);
add(txtBayar);


kembaliLabel=
new JLabel("Kembalian");
kembaliLabel.setBounds(30,420,100,30);
add(kembaliLabel);

txtKembali=
new JTextField();
txtKembali.setBounds(120,420,150,30);
add(txtKembali);


btnBayar=
new JButton("Hitung");
btnBayar.setBounds(320,380,120,30);
add(btnBayar);



btnTambah.addActionListener(
new ActionListener(){

public void actionPerformed(
ActionEvent e
){

String barang=
txtBarang.getText();

int harga=
Integer.parseInt(
txtHarga.getText()
);

int qty=
Integer.parseInt(
txtQty.getText()
);

int subtotal=
harga*qty;

model.addRow(
new Object[]{
barang,
harga,
qty,
subtotal
}
);

totalBelanja+=subtotal;

txtTotal.setText(
String.valueOf(
totalBelanja
)
);

}

}
);



btnBayar.addActionListener(
new ActionListener(){

public void actionPerformed(
ActionEvent e
){

int bayar=
Integer.parseInt(
txtBayar.getText()
);

int kembali=
bayar-totalBelanja;

txtKembali.setText(
String.valueOf(
kembali
)
);

JOptionPane.showMessageDialog(
null,
"Pembayaran Berhasil"
);

}

}
);


setVisible(true);
setDefaultCloseOperation(
EXIT_ON_CLOSE
);

}

}