package application;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class searchResultsController extends Controller {

	@FXML
	private ImageView img1;
	@FXML
	private ImageView img2;
	@FXML
	private ImageView img3;
	@FXML
	private ImageView img4;
	@FXML
	private ImageView img5;
	@FXML
	private Label city1;
	@FXML
	private Label city2;
	@FXML
	private Label city3;
	@FXML
	private Label city4;
	@FXML
	private Label city5;
	@FXML
	private Label cijena1;
	@FXML
	private Label cijena2;
	@FXML
	private Label cijena3;
	@FXML
	private Label cijena4;
	@FXML
	private Label cijena5;
	@FXML
	private Label avail1;
	@FXML
	private Label avail2;
	@FXML
	private Label avail3;
	@FXML
	private Label avail4;
	@FXML
	private Label avail5;
	@FXML
	private Button bPrev;
	@FXML
	private Button bNext;
	@FXML
	private AnchorPane containerMainItemsResults;
	@FXML
	private AnchorPane containerMainItems;
	static int counter = 0;
	//>>>>>odavdje<<<<<//
	private LocalDate pass_from;
	private LocalDate pass_to;
	//>>>>>dovdje<<<<<//
	@FXML
	public void initialize() {

	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DataBaseConnectivity.getInstance().getConnection();
		return conn;
	}

	

	public void img1Clicked() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accomodation.fxml"));
			AnchorPane ap = fxmlLoader.load();
			AccomodationController acc = (AccomodationController) fxmlLoader.getController();
			//>>>>>odavdje<<<<<//
			acc.setData(id_vector.elementAt(0),pass_from,pass_to);
			//>>>>>dovdje<<<<<//
			containerMainItemsResults.getChildren().setAll(ap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void img2Clicked() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accomodation.fxml"));
			AnchorPane ap = fxmlLoader.load();
			AccomodationController acc = (AccomodationController) fxmlLoader.getController();
			//>>>>>odavdje<<<<<//
			acc.setData(id_vector.elementAt(1),pass_from,pass_to);
			//>>>>>dovdje<<<<<//
			containerMainItemsResults.getChildren().setAll(ap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void img3Clicked() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accomodation.fxml"));
			AnchorPane ap = fxmlLoader.load();
			AccomodationController acc = (AccomodationController) fxmlLoader.getController();
			//>>>>>odavdje<<<<<//
			acc.setData(id_vector.elementAt(2),pass_from,pass_to);
			//>>>>>odavdje<<<<<//
			containerMainItemsResults.getChildren().setAll(ap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void img4Clicked() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accomodation.fxml"));
			AnchorPane ap = fxmlLoader.load();
			AccomodationController acc = (AccomodationController) fxmlLoader.getController();
			//>>>>>odavdje<<<<<//
			acc.setData(id_vector.elementAt(3),pass_from,pass_to);
			//>>>>>dovdje<<<<<//
			containerMainItemsResults.getChildren().setAll(ap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void img5Clicked() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Accomodation.fxml"));
			AnchorPane ap = fxmlLoader.load();
			AccomodationController acc = (AccomodationController) fxmlLoader.getController();
			//>>>>>odavdje<<<<<//
			acc.setData(id_vector.elementAt(4),pass_from,pass_to);
			//>>>>>dovdje<<<<<//
			containerMainItemsResults.getChildren().setAll(ap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Dodati ulazne parametre from i to
	public void listData(Vector<Integer> ids, int startIndex,LocalDate from,LocalDate to) {
		//>>>>>odavdje<<<<<//
		pass_from = from;
		pass_to = to;
		//>>>>>dovdje<<<<<//
		PreparedStatement prepStmt = null;
		Connection conn_other = null;

		for(int i: id_vector) {
			System.out.println("EEE: "+i);
		}
		
		
		try {
			Integer foo = ids.size();
			if (foo <= 5)
				bNext.setDisable(true);
			bPrev.setDisable(true);

			conn_other = getConnection();

			Vector<String> names = new Vector<String>();
			Vector<Image> images = new Vector<Image>();
			Vector<Integer> prices = new Vector<Integer>();

			for (int i = 0; i < ids.size(); ++i) {
				prepStmt = conn_other.prepareStatement(
						"SELECT Accomodation_name,slika,cijena FROM projekat.Nekretnina WHERE ID = ?");
				prepStmt.setInt(1, ids.get(i));
				ResultSet rs = prepStmt.executeQuery();

				rs.next();
				names.add(rs.getString(1));
				images.add(new Image(rs.getBlob(2).getBinaryStream()));
				prices.add(rs.getInt(3));
			}
			// TESTIRANJE
			for (String i : names) {
				if (i != null)
					System.out.println(i);
			}
			for (Integer i : prices) {
				if (i != null)
					System.out.println(i.toString());
			}
			if (foo >= 1) {
				city1.setText(names.elementAt(0));
				img1.setImage(images.elementAt(0));
				cijena1.setText(prices.elementAt(0).toString() + "KM");
				avail1.setVisible(true);
			} else {
				img1.setDisable(true);
			}
			if (foo >= 2) {
				city2.setText(names.elementAt(1));
				img2.setImage(images.elementAt(1));
				cijena2.setText(prices.elementAt(1).toString() + "KM");
				avail2.setVisible(true);
			} else {
				img2.setDisable(true);
			}
			if (foo >= 3) {
				city3.setText(names.elementAt(2));
				img3.setImage(images.elementAt(2));
				cijena3.setText(prices.elementAt(2).toString() + "KM");
				avail3.setVisible(true);
			} else {
				img3.setDisable(true);
			}
			if (foo >= 4) {
				city4.setText(names.elementAt(3));
				img4.setImage(images.elementAt(3));
				cijena4.setText(prices.elementAt(3).toString() + "KM");
				avail4.setVisible(true);
			} else {
				img4.setDisable(true);
			}
			if (foo >= 5) {
				city5.setText(names.elementAt(4));
				img5.setImage(images.elementAt(4));
				cijena5.setText(prices.elementAt(4).toString() + "KM");
				avail5.setVisible(true);
			} else {
				img5.setDisable(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnectivity.closeAll(null, prepStmt, conn_other);
		}
	}

}