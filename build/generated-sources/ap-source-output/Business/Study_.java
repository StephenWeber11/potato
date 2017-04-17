package Business;

import Business.Answer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-16T20:51:39")
@StaticMetamodel(Study.class)
public class Study_ { 

    public static volatile SingularAttribute<Study, Date> dateCreated;
    public static volatile SingularAttribute<Study, Integer> numofParticipants;
    public static volatile SingularAttribute<Study, String> question;
    public static volatile SingularAttribute<Study, Integer> requestedParticipants;
    public static volatile SingularAttribute<Study, String> studyName;
    public static volatile SingularAttribute<Study, String> imageURL;
    public static volatile SingularAttribute<Study, String> answerType;
    public static volatile ListAttribute<Study, Answer> answers;
    public static volatile SingularAttribute<Study, String> description;
    public static volatile SingularAttribute<Study, String> studyCode;
    public static volatile SingularAttribute<Study, String> email;
    public static volatile SingularAttribute<Study, String> status;

}