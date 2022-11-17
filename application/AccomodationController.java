package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AccomodationController extends Controller {
	//>>>>>odavdje<<<<<//
	private static int tmp_id = 0;
	//>>>>>dovdje<<<<<//
	@FXML
	private ImageView img;

	@FXML
	private Label name;

	@FXML
	private Label description;

	@FXML
	private Label city;

	@FXML
	private Label price;

	@FXML
	private RadioButton wifi_rb;

	@FXML
	private RadioButton tv_rb;

	@FXML
	private RadioButton ac_rb;

	@FXML
	private RadioButton balcony_rb;

	@FXML
	private RadioButton bath_rb;

	@FXML
	private RadioButton kitchen_rb;

	@FXML
	private RadioButton parking_rb;

	@FXML
	private RadioButton pool_rb;

	@FXML
	private Label in_advance;

	@FXML
	private Label fee;

	@FXML
	private Button book_now;

	@FXML
	private Button back;
	@FXML
	private AnchorPane containerMainItemsAccomodation;
	//>>>>>odavdje<<<<<//
	PreparedStatement prepStmt = null;
	Connection conn_other = null;
	private LocalDate from;
	private LocalDate to;
	//>>>>>dovdje<<<<<//

	@FXML
	public void initialize() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DataBaseConnectivity.getInstance().getConnection();
		return conn;
	}

	public void backClicked() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchResults.fxml"));
			AnchorPane ap = fxmlLoader.load();
			searchResultsController src = (searchResultsController) fxmlLoader.getController();
			//>>>>>odavdje<<<<<//
			src.listData(id_vector, 0, from, to);
			//>>>>>dovdje<<<<<//
			containerMainItemsAccomodation.getChildren().setAll(ap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//>>>>>odavdje<<<<<//
	public void bookIsClicked() {
		// insert into rezervacija - this id - date from - date to

		if (CurrentUser.cuser.isEmpty()) {
			// nije ulogovan
			Alert a = new Alert(AlertType.WARNING);
			a.setTitle("Unable to book");
			a.setContentText("User has to be logged in to make reservations. Log in and try again.");
			a.setHeaderText("Please log in first!");
			a.show();
		} else {
			boolean payNow = false;
			int cf = 0;
			int user_balance = 0;
			long numberOfDays = 0;
			int oneDayPrice = 0;
			long total_price = 0;
			try {
				// cancellation fee and payment in advance
				conn_other = getConnection();
				prepStmt = conn_other.prepareStatement("SELECT CF,OPIA,cijena FROM projekat.Nekretnina WHERE ID = ?");
				prepStmt.setInt(1, tmp_id);
				ResultSet rs = prepStmt.executeQuery();
				rs.next();
				if (rs.getInt("OPIA") == 1) {
					payNow = true;
				}
				cf = rs.getInt("CF");

				// ukupna cijena za taj period
				oneDayPrice = rs.getInt("cijena");
				System.out.println("Datum: " + from.toString());
				numberOfDays = ChronoUnit.DAYS.between(from, to);
				total_price = oneDayPrice * numberOfDays;

				// ispitivanje balansa
				String user_name = (String) (CurrentUser.cuser.keySet().toArray()[0]);
				System.out.println("User: " + user_name + " ID_N: " + tmp_id);

				prepStmt = conn_other.prepareStatement("SELECT Balance,ID FROM projekat.Korisnik WHERE Kor_ime = ?");
				prepStmt.setString(1, user_name);
				rs = prepStmt.executeQuery();
				rs.next();
				int user_id = rs.getInt("ID");
				user_balance = rs.getInt("Balance");

				// checking if user has enough to cancel & checking if user has enough to pay in
				// advance
				if ((cf != 0 && user_balance < cf) || (payNow && user_balance < total_price)) {
					Alert a = new Alert(AlertType.WARNING);
					a.setTitle("Unable to book!");
					a.setHeaderText("Insufficient funds!");
					a.setContentText("Sorry for the inconvenience, try adding money to your online wallet.");
					a.show();
				} else {
					// ima dovoljno sredstava ili nema placanja unaprijed
					prepStmt = conn_other.prepareStatement(
							"INSERT INTO projekat.Rezervacija (ID_N, ID_K, pocetak, kraj, Reg, popust, Active) VALUES (?, ?, ?, ?, '0', '0', '1')");
					// id smjestaja
					prepStmt.setInt(1, tmp_id);
					// id korisnika
					prepStmt.setInt(2, user_id);
					// from datum
					prepStmt.setDate(3, Date.valueOf(from));
					// to datum
					prepStmt.setDate(4, Date.valueOf(to));
					prepStmt.executeUpdate();

					if (payNow) {
						prepStmt = conn_other
								.prepareStatement("UPDATE projekat.Korisnik SET Balance = Balance - ? WHERE ID = ?");
						prepStmt.setLong(1, total_price);
						prepStmt.setInt(2, user_id);
						prepStmt.executeUpdate();
					}

					Alert a = new Alert(AlertType.INFORMATION);
					a.setTitle("Success");
					a.setHeaderText("Accomodation was successfully booked!");
					a.setContentText("Have a nice trip!");
					a.show();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DataBaseConnectivity.closeAll(null, prepStmt, conn_other);
			}
		}
	}
	//>>>>>dovdje<<<<<//
	
	private void setRadioButtons(int id) {
		try {
			Vector<Integer> rb_id = new Vector<Integer>();
			prepStmt = conn_other
					.prepareStatement("SELECT ID_K FROM projekat.Nekretnina_Karakteristike WHERE ID_N = ?");
			prepStmt.setInt(1, id);
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				rb_id.add(rs.getInt("ID_K"));
			}
			if (rb_id.contains(6)) {
				// bazen
				pool_rb.setSelected(true);
			}

			if (rb_id.contains(7)) {
				// wifi
				wifi_rb.setSelected(true);
			}
			if (rb_id.contains(8)) {
				// parking
				parking_rb.setSelected(true);
			}
			if (rb_id.contains(9)) {
				// kupatilo
				bath_rb.setSelected(true);
			}
			if (rb_id.contains(10)) {
				// kuhinja
				kitchen_rb.setSelected(true);
			}
			if (rb_id.contains(11)) {
				// tv
				tv_rb.setSelected(true);
			}
			if (rb_id.contains(12)) {
				// balkon
				balcony_rb.setSelected(true);
			}
			if (rb_id.contains(13)) {
				// klima
				ac_rb.setSelected(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnectivity.closeAll(null, prepStmt, conn_other);
		}
	}
	//>>>>>dodati ulazne parametre start i end<<<<<//
	public void setData(int id, LocalDate start, LocalDate end) {
		//>>>>>odavdje<<<<<//
		tmp_id = id;
		from = start;
		to = end;
		//>>>>>dovdje<<<<<//
		try {
			conn_other = getConnection();

			prepStmt = conn_other.prepareStatement(
					"SELECT Accomodation_name,slika,cijena,opis,OPIA,CF,ID_L FROM projekat.Nekretnina WHERE ID = ?");
			prepStmt.setInt(1, id);

			ResultSet rs = prepStmt.executeQuery();
			rs.next();
			name.setText(rs.getString("Accomodation_name"));
			img.setImage(new Image(rs.getBlob("slika").getBinaryStream()));
			Integer tmp = rs.getInt("cijena");
			price.setText(tmp.toString() + "KM");
			description.setText(rs.getString("opis"));

			if (rs.getInt("OPIA") == 1)
				in_advance.setText("YES");
			else
				in_advance.setText("NO");

			tmp = rs.getInt("CF");
			fee.setText(tmp.toString() + "KM");
			tmp = rs.getInt("ID_L");

			prepStmt = conn_other.prepareStatement("SELECT Naziv FROM projekat.Lokacija WHERE ID = ?");
			prepStmt.setInt(1, tmp);
			rs = prepStmt.executeQuery();
			rs.next();
			city.setText(rs.getString("Naziv"));
			setRadioButtons(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnectivity.closeAll(null, prepStmt, conn_other);
		}
	}
}