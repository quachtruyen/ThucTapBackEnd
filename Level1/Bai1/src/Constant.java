public class Constant {
    public enum rank {
        Y("Yếu"),
        TB("Trung bình"),
        K("Khá"),
        G("giỏi"),
        XS("Xuất sắc"),
        KTT("Điểm không tồn tại");

        public final String label;
        rank(String s) {
            this.label = s;
        }

        public String getLabel() {
            return label;
        }
    }
}
