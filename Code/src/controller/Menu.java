package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

import controller.LevelCreator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu extends Application {

	private static final int HEIGHT = 700;
	private static final int WIDTH = 1200;
	protected Stage primaryStage;
	private Game game;

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.game = new Game(this);

		this.primaryStage = primaryStage;
		menuSet();
		this.primaryStage.show();
	}

	public void menuSet() {
		BorderPane root = new BorderPane();

		VBox menubuttons = new VBox();

		Button aboutbutton = new Button("ABOUT");
		aboutbutton.setMinSize(120, 50);

		Button playbutton = new Button("PLAY");
		playbutton.setMinSize(120, 50);

		Button controlbutton = new Button("CONTROLS");
		controlbutton.setMinSize(120, 50);

		Button buildbutton = new Button("LEVEL BUILDER");
		buildbutton.setMinSize(120, 50);

		menubuttons.getChildren().addAll(aboutbutton, playbutton, controlbutton, buildbutton);

		VBox.setMargin(aboutbutton, new Insets(0, 0, 10, 10));
		VBox.setMargin(playbutton, new Insets(0, 0, 10, 10));
		VBox.setMargin(controlbutton, new Insets(0, 0, 10, 10));
		VBox.setMargin(buildbutton, new Insets(0, 0, 10, 10));

		BorderPane.setMargin(menubuttons, new Insets(250, 10, 10, 540));

		root.setCenter(menubuttons);
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		scene.getStylesheets().add("resources/styles/menu.css");

		File fr;
		fr = new File("resources/images/other/menuback.jpg");

		try {
			URL url = fr.toURI().toURL();
			Image image = new Image(url.toString());
			BackgroundImage backimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
					new BackgroundSize(WIDTH, HEIGHT, false, false, false, false));
			root.setBackground(new Background(backimage));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Button OnClicks
		aboutbutton.setOnAction(click -> {
			BorderPane about = new BorderPane();
			Text title = new Text("DOCUMENTATION");
			ScrollPane body = new ScrollPane();

			Button backbutton = new Button("MENU");
			backbutton.setMinSize(120, 50);
			backbutton.setOnAction(click2 -> {
				try {
					start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			about.setTop(title);
			about.setCenter(body);
			about.setBottom(backbutton);

			BorderPane.setMargin(backbutton, new Insets(0, 0, 20, 1030));

			Scene scene2 = new Scene(about, WIDTH, HEIGHT);

			this.primaryStage.setTitle("PLANTS VS ZOMBIES: DOCUMENTATION");
			this.primaryStage.setScene(scene2);
		});

		playbutton.setOnAction(click -> {
			HBox options = new HBox();

			Button backbutton = new Button("MENU");
			backbutton.setMinSize(120, 50);
			backbutton.setOnAction(click2 -> {
				try {
					start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			Button classicmode = new Button("CLASSIC");
			classicmode.setMinSize(120, 50);
			classicmode.setOnAction(click2 -> {
				this.primaryStage.setScene(game.getScene());
			});

			Button custommode = new Button("CUSTOM LEVELS");
			custommode.setMinSize(120, 50);

			Button loadgame = new Button("LOAD GAME");
			loadgame.setMinSize(120, 50);
			loadgame.setOnAction(click2 -> {
				String[] savedgames = new File("src/savefiles").list();

				ChoiceDialog<String> dialog = new ChoiceDialog<>("", savedgames);
				dialog.setTitle("Load Game");
				dialog.setContentText("Select a Saved game file");

				Optional<String> result = dialog.showAndWait();
				result.ifPresent(option -> {
					ObjectInputStream input;
					try {
						input = new ObjectInputStream(new FileInputStream("src/savefiles/" + result.get()));
						Game loadedgame = (Game) input.readObject();
						this.primaryStage.setScene(loadedgame.getScene());
						this.primaryStage.show();
						dialog.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				});

			});

			options.getChildren().addAll(classicmode, custommode, loadgame);

			HBox.setMargin(classicmode, new Insets(0, 10, 0, 0));
			HBox.setMargin(custommode, new Insets(0, 10, 0, 10));
			HBox.setMargin(loadgame, new Insets(0, 0, 0, 10));

			BorderPane.setMargin(options, new Insets(325, 10, 10, 405));
			BorderPane.setMargin(backbutton, new Insets(0, 0, 20, 1030));

			((BorderPane) primaryStage.getScene().getRoot()).setCenter(options);
			((BorderPane) primaryStage.getScene().getRoot()).setBottom(backbutton);
		});

		buildbutton.setOnAction(click -> {
			LevelCreator lvlCreate = new LevelCreator(this);
			this.primaryStage.setTitle("PLANTS VS ZOMBIES: LEVEL BUILDER");
			this.primaryStage.setScene(lvlCreate.getLevelCreatorScene());
		});

		primaryStage.setTitle("PLANTS VS ZOMBIES: THE BOOTLEG EDITION");
		primaryStage.setScene(scene);

	}

	public static void main(String args[]) {
		launch(args);
	}
}
