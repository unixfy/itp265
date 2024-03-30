/**
 * This class represents a generic "Being".
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */
public class Being {
    //    Since these two constants should be globally the same, it is safe to make them "static'
    public static final int DEFAULT_NUM_QUARTS = 5;
    public static final String DEFAULT_EMOJI = "🧍‍♂️";
    //    instance variables (required from EdStem)
    protected String name;
    protected String icon;
    protected int quarts;

    /**
     * "master" constructor, using setter methods
     *
     * @param name   desired Being name
     * @param quarts desired Being qts of blood
     * @param icon   desired being icon (emoji)
     */
    public Being(String name, int quarts, String icon) {
        this.setName(name);
        this.setQuarts(quarts);
        this.setIcon(icon);
    }

    /**
     * constructor that uses the default emoji constant
     *
     * @param name   desired Being name
     * @param quarts desired Being qts of blood
     */
    public Being(String name, int quarts) {
        this(name, quarts, DEFAULT_EMOJI);
    }

    /**
     * constructor that uses the default quarts
     *
     * @param name desired Being name
     * @param icon desired being icon (emoji)
     */
    public Being(String name, String icon) {
        this(name, DEFAULT_NUM_QUARTS, icon);
    }

    /**
     * constructor that uses the default quarts and icon
     *
     * @param name desired Being name
     */
    public Being(String name) {
        this(name, DEFAULT_NUM_QUARTS, DEFAULT_EMOJI);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getQuarts() {
        return quarts;
    }

    public boolean setQuarts(int quarts) {
//        don't allow quarts to go below 0 (as required by edstem instructions)
        if (quarts >= 0) {
            this.quarts = quarts;
            return true;
        } else {
            return false;
        }
    }

    protected void increaseQuarts(int num) {
        this.setQuarts(this.getQuarts() + num);
    }

    protected boolean decreaseQuarts(int num) {
        return this.setQuarts(this.getQuarts() - num);
    }

    public String toString() {
        return this.getIcon() + " " + this.getName() + " has " + this.getQuarts() + " quarts of blood";
    }

    //    equals and hashcode generated by intelliJ
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Being being = (Being) o;

        if (getQuarts() != being.getQuarts()) return false;
        if (getName() != null ? !getName().equals(being.getName()) : being.getName() != null) return false;
        return getIcon() != null ? getIcon().equals(being.getIcon()) : being.getIcon() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getIcon() != null ? getIcon().hashCode() : 0);
        result = 31 * result + getQuarts();
        return result;
    }
}
