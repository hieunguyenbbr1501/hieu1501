package application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class AddRemoveController implements Initializable {
	Word source = new Word("data.txt");
	ArrayList<String> wordsource = source.createWordList();
	@FXML
	private TextField addField;
	@FXML
	private TextField pronounceField;
	@FXML
	private TextArea meaningArea;
	@FXML 
	private Button addButton;
	@FXML
	private TextField removeField;
	@FXML
	private Button removeButton;
	@FXML
	private TextArea notiArea;
	public void removerefresh()
	{
		removeField.setText("");
		wordsource=source.createWordList();
	}
	public void addrefresh()
	{
		addField.setText("");
		pronounceField.setText("");
		meaningArea.setText("");
		wordsource=source.createWordList();
	}
	public void addword()
	{
		addButton.setOnAction(e->{
			ArrayList<String> list = new ArrayList<String>();
			String addedword=addField.getText();
			String addedpronounce = pronounceField.getText();
			String addedmeaning = meaningArea.getText();
			if(wordsource.contains(addedword))
			{
				notiArea.setText("Từ đã có sẵn, không cần thêm!");
				addrefresh();
			}
			else {
				list.add(addedword);
				list.add(addedpronounce);
				list.add(addedmeaning);
				source.addWord(list);
				notiArea.setText("Thêm từ thành công!!!");
				addrefresh();
			}
			
		});
	}
	public void removeword()
	{
		removeButton.setOnAction(e->{
			if(wordsource.contains(removeField.getText()))
			{
				source.removeword(removeField.getText());
				notiArea.setText("Xóa từ thành công!!");
			}
			else
			{
				notiArea.setText("Không tìm thấy từ cần xóa!!!");
			}
			
		});
		removerefresh();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		addword();
		removeword();
	}
	
}
