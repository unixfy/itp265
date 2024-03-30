/**
 * This is an enum that represents the different forms of a vampire. (from edstem)
 *
 * @author Alex Wang
 * Spring 2024, ITP 265, coffee
 * Email: awang756@usc.edu
 */

public enum VampireForm {
    VAMPIRE("🧛🏼", "Vampire", 3, 1, 2),
    VAMPIRE_BAT("🦇", "Vampire_Bat", 1, 2, 3),
    VAMPIRE_WOLF("🐺", "Vampire_Wolf", 2, 3, 1);
    private final String emoji;
    private final String form;
    private final int garlicDamage;
    private final int crossDamage;
    private final int sunlightDamage;

    VampireForm(String icon, String form, int garlic, int cross, int sun) {
        emoji = icon;
        this.form = form;
        this.garlicDamage = garlic;
        this.crossDamage = cross;
        this.sunlightDamage = sun;
    }

    public static VampireForm getRandom() {
        return VampireForm.values()[(int) (Math.random() * VampireForm.values().length)];
    }

    public static VampireForm getWinner(VampireForm p1, VampireForm p2) {
        VampireForm winner;
        if (p1 == p2) winner = p1; //tie
            // vampire beats bat
        else if (p1 == VampireForm.VAMPIRE && p2 == VampireForm.VAMPIRE_BAT ||
                p1 == VampireForm.VAMPIRE_BAT && p2 == VampireForm.VAMPIRE) {
            winner = VampireForm.VAMPIRE;
        }
        //bat beats wolf
        else if (p1 == VampireForm.VAMPIRE_BAT && p2 == VampireForm.VAMPIRE_WOLF ||
                p1 == VampireForm.VAMPIRE_WOLF && p2 == VampireForm.VAMPIRE_BAT) {
            winner = VampireForm.VAMPIRE_BAT;
        }
        // wolf beats vampire
        else {
            winner = VampireForm.VAMPIRE_WOLF;
        }
        return winner;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getForm() {
        return form;
    }

    public int getDamage(String item) {
        int damage = 0;
        if (item.equalsIgnoreCase("garlic")) {
            damage = garlicDamage;
        } else if (item.equalsIgnoreCase("cross")) {
            damage = crossDamage;
        } else if (item.equalsIgnoreCase("sunlight")) {
            damage = sunlightDamage;
        }
        return damage;
    }
}