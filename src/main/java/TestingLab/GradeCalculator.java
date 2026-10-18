package TestingLab;

public class GradeCalculator {
    public String mention(double score){
        if(score<0 || score>20){
            throw new IllegalArgumentException("note invalide : "+score);
        }
        if(score<10) return "Echec";
        if (score<12) return "Passable";
        if(score<14) return "Assez bien";
        if(score<16) return "Bien";
        return "Tres bien";
    }
}
