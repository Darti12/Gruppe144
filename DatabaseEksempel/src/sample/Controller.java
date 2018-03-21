package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Connection myConn;
    private ObservableList<String> Personer = FXCollections.observableArrayList();
    private ObservableList<String> øvelsegrupper = FXCollections.observableArrayList();
    private ObservableList<String> øvelseIGrupper = FXCollections.observableArrayList();
    private ObservableList<String> apparater = FXCollections.observableArrayList();
    private ObservableList<String> treningsøkter = FXCollections.observableArrayList();
    private ObservableList<String> øvelser = FXCollections.observableArrayList();
    private ObservableList<String> treningsøkterUtenNotat = FXCollections.observableArrayList();


    @FXML public TextField apparatNavn;
    @FXML public TextField apparatFunksjon;
    @FXML public TextField øvelseNavn;
    @FXML public TextField treningsøktDato;
    @FXML public TextField treningsøktTid;
    @FXML public TextField treningsøktPersonligForm;
    @FXML public TextField treningsøktPrestasjon;
    @FXML public TextField treningsøktVarighet;
    @FXML public TextField treningsøktInformasjon;
    @FXML private ComboBox<String> treningsøktComboBox;
    @FXML private ComboBox<String> ØvelseComboBox;
    @FXML private ComboBox<String> øvelsesgruppeComboBox;
    @FXML private ComboBox<String> ApparatComboBox;
    @FXML private ComboBox<String> registrerØvelseTreningsøkt;
    @FXML private GridPane treningsøktGridPane;
    @FXML private TextField treningsøktrader;
    @FXML private TextField registrerØvelsegruppeNavn;
    @FXML private TextField registrerØvelsegruppeBeskrivelse;
    @FXML private TextField registrerPersonNavn;
    @FXML private ListView øvelserIGruppe;
    @FXML private TableView<String> resultatlogg;
    @FXML private Spinner<Integer> registrerØvelseApparatKilo;
    @FXML private Spinner<Integer> registrerØvelseApparatSett;
    @FXML private TextArea registrerØvelseBeskrivelse;
    @FXML private ComboBox<String> addPersonTilTreningsøktComboBox1;
    @FXML private ComboBox<String> addPersonTilTreningsøktComboBox2;
    @FXML private ComboBox<String> addØvelseTilTreningsøktComboBox1;
    @FXML private ComboBox<String> addØvelseTilTreningsøktComboBox2;
    @FXML private ComboBox<String> addØvelseTilØvelsesgruppeComboBox1;
    @FXML private ComboBox<String> addØvelseTilØvelsesgruppeComboBox2;
    @FXML private ComboBox<String> addNotatTilTreningsøktComboBox;
    @FXML private TextArea addNotatTilTreningsøktTextArea;


    public Controller() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.myConn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/filiphag_prosjekt", "filiphag_server", "123abc");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    @FXML
    private void addPersonTilTreningsøkt(){
        String navn = addPersonTilTreningsøktComboBox1.getValue();
        String treningsøkt = addPersonTilTreningsøktComboBox2.getValue();


        try {
            Statement myStatement3 = myConn.createStatement();
            ResultSet rs3 = myStatement3.executeQuery("SELECT PID FROM Person WHERE Navn = '" + navn + "'");
            rs3.next();
            int PID = Integer.parseInt(rs3.getString("PID"));

            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT TøID FROM Treningsøkt WHERE Info = '" + treningsøkt + "'");
            rs2.next();
            int TøID = Integer.parseInt(rs2.getString("TøID"));

            Statement myStatement = myConn.createStatement();
            String sql = "INSERT INTO PersonITreningsøkt VALUES(" + PID + ", " +  TøID + ")";
            System.out.println(sql);
            myStatement.executeUpdate(sql);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void addØvelseTilTreningsøkt(){
        String navn = addØvelseTilTreningsøktComboBox1.getValue();
        String treningsøkt = addØvelseTilTreningsøktComboBox2.getValue();

        try {
            Statement myStatement1 = myConn.createStatement();
            ResultSet rs1 = myStatement1.executeQuery("SELECT ØvelseID FROM Øvelse WHERE Navn='" + navn + "'");
            rs1.next();
            int øvelseID = Integer.parseInt(rs1.getString("ØvelseID"));

            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT TøID FROM Treningsøkt WHERE Info='" + treningsøkt + "'");
            rs2.next();
            int TøID = Integer.parseInt(rs2.getString("TøID"));

            Statement myStatement = myConn.createStatement();
            String sql = "INSERT INTO Øvelsesøkt VALUES(" + øvelseID + ", " + TøID + ")";
            System.out.println(sql);
            myStatement.executeUpdate(sql);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void addØvelseTilØvelsesgruppe(){
        String navn = addØvelseTilØvelsesgruppeComboBox1.getValue();
        String øvelsesgruppe = addØvelseTilØvelsesgruppeComboBox2.getValue();
        try {
            Statement myStatement1 = myConn.createStatement();
            ResultSet rs1 = myStatement1.executeQuery("SELECT ØvelseID FROM Øvelse WHERE Navn='" + navn + "'");
            rs1.next();
            int øvelseID = Integer.parseInt(rs1.getString("ØvelseID"));

            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT ØGID FROM Øvelsesgruppe WHERE Navn='" + øvelsesgruppe + "'");
            rs2.next();
            int ØGID = Integer.parseInt(rs2.getString("ØGID"));

            Statement myStatement = myConn.createStatement();
            String sql = "INSERT INTO ØvelseIGruppe VALUES(" + øvelseID + ", " + ØGID + ")";
            System.out.println(sql);
            myStatement.executeUpdate(sql);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void addNotatTilTreningsøkt(){
        String treningsøkt = addNotatTilTreningsøktComboBox.getValue();
        String beskrivelse = addNotatTilTreningsøktTextArea.getText();

        try {
            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT TøID FROM Treningsøkt WHERE Info = '" + treningsøkt + "'");
            rs2.next();
            int TøID = Integer.parseInt(rs2.getString("TøID"));

            Statement myStatement3 = myConn.createStatement();
            ResultSet rs3 = myStatement3.executeQuery("SELECT MAX(NID) FROM Notat");
            rs3.next();
            int NID = Integer.parseInt(rs3.getString("MAX(NID)")) + 1;

            Statement myStatement = myConn.createStatement();
            String sql = "INSERT INTO Notat Values(" + NID + ", '" + beskrivelse + "', " + TøID + ")";
            System.out.println(sql);
            myStatement.executeUpdate(sql);
        }catch(Exception e) {
            System.out.println(e);
        }
        addNotatTilTreningsøktComboBox.setValue(null);
        addNotatTilTreningsøktTextArea.setText("");
        updateComboBoxes();
    }

    @FXML
    private void registrerApparat(){
        try {
            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT MAX(ApparatID) FROM Apparat");
            rs2.next();
            int tallID = Integer.parseInt(rs2.getString("MAX(ApparatID)")) + 1;

            String navn = apparatNavn.getText();
            String funksjon = apparatFunksjon.getText();

            Statement myStatement = myConn.createStatement();
            String sql = "Insert into Apparat VALUES(" + tallID + ", '" + navn + "', '" + funksjon +"')";
            System.out.println(sql);
            myStatement.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
        }
        updateComboBoxes();
        apparatNavn.setText("");
        apparatFunksjon.setText("");
    }

    @FXML
    private void registrerØvelse(){
        try {
            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT MAX(ØvelseID) FROM Øvelse");
            rs2.next();
            int øvelseID = Integer.parseInt(rs2.getString("MAX(ØvelseID)")) + 1;

            Statement myStatement3 = myConn.createStatement();
            ResultSet rs3 = myStatement3.executeQuery("SELECT * FROM Øvelsesgruppe WHERE Navn = '" + ØvelseComboBox.getValue() + "'");
            rs3.next();
            int gruppeID = Integer.parseInt(rs3.getString("ØGID"));

            String navn = øvelseNavn.getText();
            Statement myStatement4 = myConn.createStatement();
            String sql1 = "Insert into Øvelse VALUES(" + øvelseID + ", '" + navn + "')";
            System.out.println(sql1);
            myStatement4.executeUpdate(sql1);

            Statement myStatement5 = myConn.createStatement();
            String sql2 = "Insert into ØvelseIGruppe VALUES(" + øvelseID + ", " + gruppeID + ")";
            System.out.println(sql2);
            myStatement5.executeUpdate(sql2);

            if(ApparatComboBox.getValue() != null){
                int kilo = registrerØvelseApparatKilo.getValue();
                int sett = registrerØvelseApparatSett.getValue();

                Statement myStatement8 = myConn.createStatement();
                ResultSet rs7 = myStatement3.executeQuery("SELECT ApparatID FROM Apparat WHERE Navn = '" + ApparatComboBox.getValue() + "'");
                rs7.next();
                int ApparatID = Integer.parseInt(rs7.getString("ApparatID"));

                Statement myStatement9 = myConn.createStatement();
                String sql4 = "Insert into ØvelseMedApparat VALUES(" + øvelseID + ", " + ApparatID + ", " + kilo + ", " + sett + ")";
                System.out.println(sql4);
                myStatement9.executeUpdate(sql4);
            }else{
                Statement myStatement10 = myConn.createStatement();
                String sql5 = "Insert into Friøvelse VALUES(" + øvelseID + ", '" + registrerØvelseBeskrivelse.getText() + "')";
                System.out.println(sql5);
                myStatement10.executeUpdate(sql5);
            }

            if(registrerØvelseTreningsøkt.getValue() != null) {
                Statement myStatement6 = myConn.createStatement();
                ResultSet rs6 = myStatement3.executeQuery("SELECT TøID FROM Treningsøkt WHERE Info='" + registrerØvelseTreningsøkt.getValue() + "'");
                rs6.next();
                int TøID = Integer.parseInt(rs6.getString("TøID"));

                Statement myStatement7 = myConn.createStatement();
                String sql3 = "Insert into Øvelsesøkt VALUES(" + øvelseID + ", " + TøID + ")";
                System.out.println(sql3);
                myStatement7.executeUpdate(sql3);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        øvelseNavn.setText("");
        ØvelseComboBox.setValue(null);
        ApparatComboBox.setValue(null);
        registrerØvelseBeskrivelse.setText("");
        registrerØvelseTreningsøkt.setValue(null);
    }

    @FXML
    private void registrerFriØvelse(){
        if(ApparatComboBox.getValue() != null){
            registrerØvelseBeskrivelse.setDisable(true);
            registrerØvelseApparatKilo.setDisable(false);
            registrerØvelseApparatSett.setDisable(false);
        }else{
            registrerØvelseApparatKilo.setDisable(true);
            registrerØvelseApparatSett.setDisable(true);
            registrerØvelseBeskrivelse.setDisable(false);
        }
    }

    @FXML
    private void registrerTreningsøkt(){
        try {
            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT MAX(TøID) FROM Treningsøkt");
            rs2.next();
            int tallID = Integer.parseInt(rs2.getString("MAX(TøID)")) + 1;

            String person = treningsøktComboBox.getValue();
            Statement myStatement3 = myConn.createStatement();
            ResultSet rs3 = myStatement3.executeQuery("SELECT PID FROM Person WHERE Navn='" + person + "'");
            rs3.next();
            int PID = Integer.parseInt(rs3.getString("PID"));

            String treningsøktdato = treningsøktDato.getText();
            String treningsøkttid = treningsøktTid.getText();
            int personligform = Integer.parseInt(treningsøktPersonligForm.getText());
            int prestasjon = Integer.parseInt(treningsøktPrestasjon.getText());
            int varighet = Integer.parseInt(treningsøktVarighet.getText());
            String info = treningsøktInformasjon.getText();

            Statement myStatement = myConn.createStatement();
            String sql = "INSERT INTO Treningsøkt VALUES(" + tallID + ",'" + treningsøktdato + "','" + treningsøkttid + "'," + personligform + "," + prestasjon + "," + varighet + ",'" + info + "'," + PID + ")";
            System.out.println(sql);
            myStatement.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void updateResultlog(){
        resultatlogg.getItems().clear();

    }

    @FXML
    public void updatetreningsøktGridpane(){
        treningsøktGridPane.getChildren().retainAll(treningsøktGridPane.getChildren().get(0));
        Label Tid = new Label("Tid");
        Tid.setAlignment(Pos.CENTER);
        Tid.setMaxWidth(Double.MAX_VALUE);
        Label PersonligForm = new Label("PersonligForm");
        PersonligForm.setAlignment(Pos.CENTER);
        PersonligForm.setMaxWidth(Double.MAX_VALUE);
        Label Prestasjon = new Label("Prestasjon");
        Prestasjon.setAlignment(Pos.CENTER);
        Prestasjon.setMaxWidth(Double.MAX_VALUE);
        Label Varighet = new Label("Varighet");
        Varighet.setAlignment(Pos.CENTER);
        Varighet.setMaxWidth(Double.MAX_VALUE);
        Label Informasjon = new Label("Informasjon");
        Informasjon.setAlignment(Pos.CENTER);
        Informasjon.setMaxWidth(Double.MAX_VALUE);
        Label Notat = new Label("Notat");
        Notat.setAlignment(Pos.CENTER);
        Notat.setMaxWidth(Double.MAX_VALUE);
        treningsøktGridPane.add(Tid,0, 0);
        treningsøktGridPane.add(PersonligForm,1, 0);
        treningsøktGridPane.add(Prestasjon,2, 0);
        treningsøktGridPane.add(Varighet,3, 0);
        treningsøktGridPane.add(Informasjon,4, 0);
        treningsøktGridPane.add(Notat,5, 0);
        try {
            Statement myStatement = myConn.createStatement();
            ResultSet rs = myStatement.executeQuery("SELECT * " +
                    "FROM Treningsøkt AS T JOIN Notat AS N ON (N.TøID = T.TøID)" +
                    "ORDER BY T.Dato, T.Tid ASC");
            for(int i = 1; i < Integer.parseInt(treningsøktrader.getText())+1; i++){
                rs.next();

                Label cellTid = new Label(rs.getString("Tid"));
                cellTid.setAlignment(Pos.CENTER);
                cellTid.setMaxWidth(Double.MAX_VALUE);
                treningsøktGridPane.add(cellTid,0, i);

                Label cellPersonligForm = new Label(rs.getString("PersonligForm"));
                cellPersonligForm.setAlignment(Pos.CENTER);
                cellPersonligForm.setMaxWidth(Double.MAX_VALUE);
                treningsøktGridPane.add(cellPersonligForm,1, i);

                Label cellPrestasjon = new Label(rs.getString("Prestasjon"));
                cellPrestasjon.setAlignment(Pos.CENTER);
                cellPrestasjon.setMaxWidth(Double.MAX_VALUE);
                treningsøktGridPane.add(cellPrestasjon,2, i);

                Label cellVarighet = new Label(rs.getString("Varighet"));
                cellVarighet.setAlignment(Pos.CENTER);
                cellVarighet.setMaxWidth(Double.MAX_VALUE);
                treningsøktGridPane.add(cellVarighet,3, i);

                Label cellInformasjon = new Label(rs.getString("Info"));
                cellInformasjon.setAlignment(Pos.CENTER);
                cellInformasjon.setMaxWidth(Double.MAX_VALUE);
                treningsøktGridPane.add(cellInformasjon,4, i);

                Label cellNotat = new Label(rs.getString("Beskrivelse"));
                cellNotat.setAlignment(Pos.CENTER);
                cellNotat.setMaxWidth(Double.MAX_VALUE);
                treningsøktGridPane.add(cellNotat,5, i);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void registrerØvelsesgruppe(){
        String navn = registrerØvelsegruppeNavn.getText();
        String beskrivelse = registrerØvelsegruppeBeskrivelse.getText();
        try {
            Statement myStatement1 = myConn.createStatement();
            ResultSet rs = myStatement1.executeQuery("SELECT MAX(ØGID) FROM Øvelsesgruppe");
            rs.next();
            int ØvelsesgruppeID = Integer.parseInt(rs.getString("MAX(ØGID)")) + 1;

            Statement myStatement2 = myConn.createStatement();
            String sql = "INSERT INTO Øvelsesgruppe VALUES(" + ØvelsesgruppeID + ",'" + navn + "','" + beskrivelse + "')";
            System.out.println(sql);
            myStatement2.executeUpdate(sql);
        }catch(Exception e){

        }
        updateComboBoxes();
        registrerØvelsegruppeNavn.setText("");
        registrerØvelsegruppeBeskrivelse.setText("");
    }

    @FXML
    private void registrerPerson(){
        try{
            Statement myStatement1 = myConn.createStatement();
            ResultSet rs1 = myStatement1.executeQuery("SELECT MAX(PID) FROM Person");
            rs1.next();

            int PID = Integer.parseInt(rs1.getString("MAX(PID)")) + 1;
            String navn = registrerPersonNavn.getText();

            Statement myStatement2 = myConn.createStatement();
            String sql = "INSERT INTO Person VALUES(" + PID + ", '" + navn + "')";
            System.out.println(sql);
            myStatement2.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
        }
        updateComboBoxes();
        registrerPersonNavn.setText("");
    }

    @FXML
    public void finnØvelserIGrupper(){
        øvelserIGruppe.getItems().clear();
        øvelseIGrupper.clear();
        String gruppe = øvelsesgruppeComboBox.getValue();
        try {
            Statement myStatement1 = myConn.createStatement();
            ResultSet rs1 = myStatement1.executeQuery("SELECT ØGID FROM Øvelsesgruppe WHERE Øvelsesgruppe.Navn = '" + gruppe + "'");
            rs1.next();
            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT Øvelse.Navn FROM (Øvelse NATURAL JOIN ØvelseIGruppe) JOIN Øvelsesgruppe WHERE ØvelseIGruppe.ØGID=" + rs1.getString("ØGID") + " GROUP BY Øvelse.Navn");
            while(rs2.next()){
                øvelseIGrupper.add(rs2.getString("Navn"));
            }
            øvelserIGruppe.setItems(øvelseIGrupper);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    private void updateComboBoxes(){
        treningsøktComboBox.getItems().clear();
        øvelsesgruppeComboBox.getItems().clear();
        ApparatComboBox.getItems().clear();
        treningsøktComboBox.getItems().clear();
        addPersonTilTreningsøktComboBox1.getItems().clear();
        addPersonTilTreningsøktComboBox2.getItems().clear();
        addØvelseTilTreningsøktComboBox1.getItems().clear();
        addØvelseTilTreningsøktComboBox2.getItems().clear();
        addØvelseTilØvelsesgruppeComboBox1.getItems().clear();
        addØvelseTilØvelsesgruppeComboBox2.getItems().clear();
        addNotatTilTreningsøktComboBox.getItems().clear();

        Personer.clear();
        øvelsegrupper.clear();
        apparater.clear();
        treningsøkter.clear();
        treningsøkterUtenNotat.clear();
        øvelser.clear();
        try{
            //personer comboxer fylles ut
            Statement myStatement1 = myConn.createStatement();
            ResultSet rs1 = myStatement1.executeQuery("SELECT Navn FROM Person");
            while (rs1.next()) {
                String navn = rs1.getString("Navn");
                Personer.add(navn);
            }
            treningsøktComboBox.setItems(Personer);
            addPersonTilTreningsøktComboBox1.setItems(Personer);

            //øvelsesgruppe comboboxer fylles ut
            Statement myStatement2 = myConn.createStatement();
            ResultSet rs2 = myStatement2.executeQuery("SELECT Navn FROM Øvelsesgruppe");
            while (rs2.next()) {
                String navn = rs2.getString("Navn");
                øvelsegrupper.add(navn);
            }
            øvelsesgruppeComboBox.setItems(øvelsegrupper);
            ØvelseComboBox.setItems(øvelsegrupper);
            addØvelseTilØvelsesgruppeComboBox2.setItems(øvelsegrupper);

            //apparat comboboxer fylles ut
            Statement myStatement3 = myConn.createStatement();
            ResultSet rs3 = myStatement3.executeQuery("SELECT Navn FROM Apparat");
            while (rs3.next()) {
                String navn = rs3.getString("Navn");
                apparater.add(navn);
            }
            apparater.add(null);
            ApparatComboBox.setItems(apparater);

            //treningsøkter comboboxer fylles ut
            Statement myStatement4 = myConn.createStatement();
            ResultSet rs4 = myStatement1.executeQuery("SELECT Info FROM Treningsøkt ORDER BY Dato ASC");
            while (rs4.next()) {
                String navn = rs4.getString("Info");
                treningsøkter.add(navn);
            }
            treningsøkter.add(null);
            registrerØvelseTreningsøkt.setItems(treningsøkter);
            addPersonTilTreningsøktComboBox2.setItems(treningsøkter);
            addØvelseTilTreningsøktComboBox2.setItems(treningsøkter);

            //øvelse comboboxer fylles ut
            Statement myStatement5 = myConn.createStatement();
            ResultSet rs5 = myStatement5.executeQuery("SELECT Navn FROM Øvelse");
            while (rs5.next()) {
                String navn = rs5.getString("Navn");
                øvelser.add(navn);
            }
            addØvelseTilTreningsøktComboBox1.setItems(øvelser);
            addØvelseTilØvelsesgruppeComboBox1.setItems(øvelser);




            //treningsøkt uten noe notat combobox fylles ut
            Statement myStatement6 = myConn.createStatement();
            ResultSet rs6 = myStatement5.executeQuery("SELECT Info FROM Notat RIGHT JOIN Treningsøkt ON Notat.TøID=Treningsøkt.TøID WHERE Notat.NID IS NULL");
            while (rs6.next()) {
                String navn = rs6.getString("Info");
                treningsøkterUtenNotat.add(navn);
            }
            addNotatTilTreningsøktComboBox.setItems(treningsøkterUtenNotat);

        }catch(Exception e){

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateComboBoxes();
        registrerFriØvelse();

        SpinnerValueFactory<Integer> kiloverdier = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 20);
        SpinnerValueFactory<Integer> settverdier = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 2);
        this.registrerØvelseApparatKilo.setValueFactory(kiloverdier);
        this.registrerØvelseApparatSett.setValueFactory(settverdier);
    }
}
