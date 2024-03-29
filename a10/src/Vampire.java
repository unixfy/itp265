/**
 * This class represents a "vampire".
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
public class Vampire extends Being {
    private VampireForm form;
    private int points;
    private final BloodType favBlood1;
    private final BloodType favBlood2;

    public Vampire(String name, int quarts, VampireForm form, BloodType favBlood1, BloodType favBlood2) {
        super(name, quarts, form.getEmoji());
        this.form = form;
        this.points = 10;
        this.favBlood1 = favBlood1;
        this.favBlood2 = favBlood2;

//        to ensure the two blood types are different
        while (favBlood1 == favBlood2) {
            favBlood2 = BloodType.getRandom();
        }
    }

    public Vampire(String name, int quarts, VampireForm form) {
        this(name, quarts, form, BloodType.getRandom(), BloodType.getRandom());
    }

    public Vampire(String name, int quarts) {
        this(name, quarts, VampireForm.VAMPIRE);
    }

    public Vampire(String name) {
        this(name, DEFAULT_NUM_QUARTS);
    }

    public Vampire(String name, VampireForm form) {
        this(name, DEFAULT_NUM_QUARTS, form);
    }

    public VampireForm getForm() {
        return form;
    }

    public int getPoints() {
        return points;
    }

    public BloodType getFavBlood1() {
        return favBlood1;
    }

    public BloodType getFavBlood2() {
        return favBlood2;
    }

    public void changeForm() {
//        randomly select a new form
        form = VampireForm.getRandom();
        setIcon(form.getEmoji());
    }

    public void changeForm(VampireForm nextForm) {
        form = nextForm;
        setIcon(form.getEmoji());
    }

    public void earnPoints(int num) {
        points += num;
    }

    public void losePoints(int num) {
        points -= num;
    }

    public void suckBlood() {
        increaseQuarts(1);
    }

    public String toString() {
        return "Vampire " + super.toString();
    }

    //    from edStem
    public String getDisplay() {
        return getIcon() + " " + getName() + " " + getIcon();
    }
}
