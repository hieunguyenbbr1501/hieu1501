package application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class FixController implements Initializable
{
	Word source = new Word("data.txt");
	ArrayList<String> wordsource = source.createWordList();
	@FXML
	private TextField searchField;
	@FXML
	private TextField pronounceField;
	@FXML
	private TextArea meaningArea;
	@FXML
	private Button fixButton;
	@FXML
	private TextArea notiArea;
	public void fixrefresh()
	{
		fixButton.setOnAction(e->{
			searchField.setText("");
			pronounceField.setText("");
			meaningArea.setText("");
			wordsource = source.createWordList();
		});
	}
	public void fixword()
	{	wordsource = source.createWordList();
		fixButton.setOnAction(e->{
			ArrayList<String> list = new ArrayList<String>();
			if(wordsource.contains(searchField.getText()))
			{
				list.add(pronounceField.getText());
				list.add(meaningArea.getText());
				source.fixword(searchField.getText(), list);
				notiArea.setText("Sửa từ thành công!!"+"\n"+"Từ sau khi sửa: "+searchField.getText()+"\n"+"Phát âm: "+pronounceField.getText()+"\n"+"Nghĩa: "+meaningArea.getText());
				fixrefresh();
			}
			else
			{
				notiArea.setText("Từ không sẵn có, vui lòng thêm từ mới!");
				fixrefresh();
			}
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		fixword();
	}
	
}