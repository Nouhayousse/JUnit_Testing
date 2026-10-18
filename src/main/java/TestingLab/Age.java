package TestingLab;

public record Age(int value) {
    public Age{
        if(value<0 || value>150){
            throw new IllegalArgumentException("Age invalide : "+value);
        }
    }

    public boolean isAdult(){
        return value>=18;
    }
}
