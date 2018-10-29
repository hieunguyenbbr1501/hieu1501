package application;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Word {

    private String path = "";

    public Word(String path) {
        this.path = path;
    }

    public Word() {
    }

    public ArrayList<String> createWordList() {
        ArrayList<String> wordList = new ArrayList<>();

        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.length() == 0) continue;
                else {
                    if (line.charAt(0) == '@') {
                        String considerWord = "";
                        int i = 1;
                        while (i < line.length() && line.charAt(i) != '/') {
                            considerWord += line.charAt(i);
                            i++;
                        }
                        wordList.add(considerWord.trim());
                    }
                }
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lấy từ điển có vấn đề");
        } catch (IOException e) {
            System.out.println("Lấy từ điển có vấn đề");
        }

        return wordList;
    }

    public String getMeaning(String name) {
        String word = "";
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = null;
            boolean getMeaning = false;
            while ((line = br.readLine()) != null) {
                if (line.length() == 0) continue;
                else {
                    if (getMeaning == true) {
                        if (line.charAt(0) == '@') break;
                        else {
                            word += line + "\n";
                        }
                    } else {
                        if (line.charAt(0) == '@') {
                            if (line.contains(name)) {
                                getMeaning = true;

                            }
                        }
                    }
                }
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File error");
        } catch (IOException e) {
            System.out.println("io error");
        }
        return word.trim();
    }

    public String getPronounce(String name)
    {
        String Pronounce = "";
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = null;
            while ((line = br.readLine()) != null)
            {
                if (line.length() == 0) continue;
                else {
                    if(line.contains(name))
                    {
                        break;
                    }

                }
            }
            for (int i =line.indexOf('/') + 1 ; i < line.lastIndexOf('/');i++)
            {
                Pronounce+=line.charAt(i);
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("file error");
        } catch (IOException e) {
            System.out.println("io error");
        }
        return Pronounce.trim();
    }
    public void addWord(ArrayList<String> list)
    {
    	String wordforadd = new String();
    	wordforadd = '@'+list.get(0)+ " /"+list.get(1)+"/\n";
    	for(int i=2;i<list.size();i++) 
    		{
    			wordforadd+=list.get(i)+"\n";
    		}
    	try
    	{
    		FileOutputStream fos = new FileOutputStream(path, true);
    		OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("\n");
            bw.write(wordforadd);
            bw.close();
            osw.close();
            fos.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    public void removeword(String wordforremove) {
        Scanner scan;
        File tempFile = new File("tempFile.txt");
        File oldFile = new File(path);
        String line;
        try {
            FileOutputStream fos = new FileOutputStream(tempFile);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            scan = new Scanner(new File(path));
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                if (line.length() == 0)
                    continue;
                else {
                    if (line.charAt(0) == '@') {

                        if (line.trim().contains(wordforremove)) {
                            bw.newLine();
                            line=scan.nextLine();
                            while (line.charAt(0) != '@')
                            {
                                if(scan.hasNextLine())
                                    line=scan.nextLine();
                                else
                                    break;
                                if(line.length()==0)
                                    continue;
                            }

                        }
                        else {
                            bw.write(line);
                            bw.newLine();
                        }
                    }
                    else {
                        bw.write(line);
                        bw.newLine();
                    }
                }
            }
                bw.close();
                osw.close();
                fos.close();
                scan.close();
                oldFile.delete();
                File dump = new File(path);
                tempFile.renameTo(dump);
        }
        catch(Exception ex)
            {
                ex.printStackTrace();
            }
    }
    public void fixword(String wordforfix,ArrayList<String>newword)
    {
    	Scanner scanner;
    	File temp = new File("temp.txt");
    	File oldfile = new File(path);
    	String line;
    	try
    	{
    		FileOutputStream fos = new FileOutputStream(temp);
    		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
    		BufferedWriter bw = new BufferedWriter(osw);
    		scanner = new Scanner(oldfile);
    		while(scanner.hasNextLine())
    		{
    			line = scanner.nextLine();
    			if(line.length()==0) continue;
    			else {
    				if(line.charAt(0)=='@')
    				{
    					if(line.trim().contains(wordforfix))
    					{
    						bw.write('@'+wordforfix+" /"+newword.get(0)+"/\n"+newword.get(1));
    						bw.newLine();
    						line = scanner.nextLine();
    						while(line.charAt(0)!='@')
    						{
    							if(scanner.hasNextLine()) line = scanner.nextLine();
    							else {
    								break;
    							}
    							if(line.length()==0) continue;
    						}
    					}
    					else
    					{
    						bw.write(line);
    						bw.newLine();
    					}
    				}
    				else {
    					bw.write(line);
    					bw.newLine();
    				}
    			}
    		}
    		bw.close();
    		osw.close();
    		fos.close();
    		scanner.close();
    		oldfile.delete();
    		File newfile = new File(path);
    		temp.renameTo(newfile);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    }

}
