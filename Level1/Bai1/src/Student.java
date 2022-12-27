public class Student {
    private String name;
    private float mark;
    private String email;
    private String rank;

    public Student(String rank) {
        this.rank = rank;
    }

    public Student(String name, float mark, String email) {
        this.name = name;
        this.mark = mark;
        this.email = email;
        rankClassification();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    private void rankClassification() {
        if (this.mark >= 0 && this.mark < 3) {
            this.rank = Constant.rank.Y.getLabel();
        } else if (this.mark < 5) {
            this.rank = Constant.rank.Y.getLabel();
        } else if (this.mark < 6.5) {
            this.rank = Constant.rank.TB.getLabel();
        } else if (this.mark < 7.5) {
            this.rank = Constant.rank.K.getLabel();
        } else if (this.mark < 9) {
            this.rank = Constant.rank.G.getLabel();
        } else if (this.mark <= 10) {
            this.rank = Constant.rank.XS.getLabel();
        } else {
            this.rank = Constant.rank.KTT.label;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", mark=" + mark +
                ", email='" + email + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}
