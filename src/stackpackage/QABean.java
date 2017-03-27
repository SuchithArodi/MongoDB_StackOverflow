package stackpackage;

import java.util.List;
/*
 * Data from the Mongo Database are stored for the use of front end 
 */
public class QABean {
int id;
String imageLink;
public String getImageLink() {
	return imageLink;
}
public void setImageLink(String imageLink) {
	this.imageLink = imageLink;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
String questionBody;
String title;
List<String>answerBody;
String fullQuestionBody;
public String getFullQuestionBody() {
	return fullQuestionBody;
}
public void setFullQuestionBody(String fullQuestionBody) {
	this.fullQuestionBody = fullQuestionBody;
}
public List<String> getAnswerBody() {
	return answerBody;
}
public void setAnswerBody(List<String> answerBody) {
	this.answerBody = answerBody;
}
public String getQuestionBody() {
	return questionBody;
}
public void setQuestionBody(String questionBody) {
	this.questionBody = questionBody;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
}

