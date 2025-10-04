import java.util.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class MenuPriceFinder {

    static class Menu {
        public String name;
        public List<MenuGroup> groups;
        public Double price; // nullable
        public Menu(String name, Double price, List<MenuGroup> groups) {
            this.name = name;
            this.price = price;
            this.groups = groups;
        }
    }

    static class MenuGroup {
        public String name;
        public List<MenuGroup> groups;
        public List<MenuItem> items;
        public Double price; // nullable
        public MenuGroup(String name, Double price, List<MenuItem> items, List<MenuGroup> groups) {
            this.name = name;
            this.price = price;
            this.items = items;
            this.groups = groups;
        }
    }

    static class MenuItem {
        public String name;
        public Double price; // nullable
        public MenuItem(String name, Double price) { this.name = name; this.price = price; }
    }

    /**
     * Public API: scan all menus and return the effective price for itemName.
     * Returns null if item is not found in any menu.
     */
    public static Double getPrice(Collection<Menu> menus, String itemName) {
        if (menus == null || itemName == null) return null;

        for (Menu menu : menus) {
            if (menu == null) continue;
            Double menuInherited = menu.price; // may be null
            if (menu.groups == null) continue;
            for (MenuGroup g : menu.groups) {
                Double found = findPriceInGroup(g, itemName, menuInherited);
                if (found != null) return found;
            }
        }
        return null;
    }

    /**
     * DFS into group and its children. inheritedPrice is the nearest ancestor price (may be null).
     * Returns matched price or null if not found under this subtree.
     */
    private static Double findPriceInGroup(MenuGroup group, String itemName, Double inheritedPrice) {
        if (group == null || itemName == null) return null;

        Double current = (group.price != null) ? group.price : inheritedPrice;

        if (group.items != null) {
            for (MenuItem it : group.items) {
                if (it != null && it.name != null && it.name.equals(itemName)) {
                    // if item has its own price, return it; otherwise return current inherited price (may be null)
                    return (it.price != null) ? it.price : current;
                }
            }
        }

        if (group.groups != null) {
            for (MenuGroup child : group.groups) {
                Double found = findPriceInGroup(child, itemName, current);
                if (found != null) return found;
            }
        }

        return null;
    }

    // sample menus (same as your earlier example)
    static final Menu LUNCH_MENU, DRINK_MENU;
    static {
        LUNCH_MENU = new Menu(
                "Lunch",
                11.50,
                asList(
                        new MenuGroup(
                                "Appetizers",
                                5.00,
                                asList(new MenuItem("Wings", null), new MenuItem("Fries", 3.50)),
                                emptyList()
                        ),
                        new MenuGroup(
                                "Entrees",
                                null,
                                asList(new MenuItem("Burger", null)),
                                asList(
                                        new MenuGroup(
                                                "Salads",
                                                7.50,
                                                asList(new MenuItem("Garden Salad", null), new MenuItem("Cobb Salad w/ Bacon", 9.00)),
                                                emptyList()
                                        ),
                                        new MenuGroup(
                                                "Toast",
                                                6.50,
                                                asList(new MenuItem("Avocado", null), new MenuItem("PB&J", null)),
                                                emptyList()
                                        )
                                )
                        )
                )
        );

        DRINK_MENU = new Menu(
                "Drinks",
                5.00,
                asList(
                        new MenuGroup(
                                "Coffees",
                                null,
                                emptyList(),
                                asList(
                                        new MenuGroup(
                                                "Iced",
                                                5.00,
                                                asList(new MenuItem("Iced Latte", 6.50), new MenuItem("Decaf Iced Coffee", null)),
                                                emptyList()
                                        ),
                                        new MenuGroup(
                                                "Hot",
                                                3.00,
                                                asList(new MenuItem("Americano", null), new MenuItem("Cup of Joe", null)),
                                                emptyList()
                                        )
                                )
                        ),
                        new MenuGroup(
                                "Sodas",
                                2.50,
                                asList(new MenuItem("Coke", null), new MenuItem("Sprite", null), new MenuItem("Ginger Ale", null)),
                                emptyList()
                        )
                )
        );
    }

    private static Double gp(Collection<Menu> menus, String itemName) {
        if(menus == null || menus.isEmpty() || itemName == null) {
            return null;
        }

        for(Menu menu : menus) {
            if(menu == null) {
                continue;
            }

            Double menuInherited = menu.price;
            if(menu.groups == null || menu.groups.isEmpty()) {
                continue;
            }

            for (MenuGroup menuGroup : menu.groups) {
                Double foundPrice = fpIg(menuGroup, itemName, menuInherited);
                if (foundPrice != null) {
                    return foundPrice;
                }
            }
        }

        return null;

    }

    private static Double fpIg(MenuGroup menuGroup, String itemName, Double menuInheritedPrice) {
        if(menuGroup == null || itemName == null) {
            return null;
        }

        Double current = (menuGroup.price != null) ? menuGroup.price : menuInheritedPrice;

        if (menuGroup.items != null) {
            for (MenuItem item : menuGroup.items) {
                if (item != null && item.name != null && item.name.equals(itemName)) {
                    return (item.price != null) ? item.price : current;
                }
            }
        }

        if (menuGroup.groups != null) {
            for (MenuGroup childGroup : menuGroup.groups) {
                Double found = fpIg(childGroup, itemName, menuInheritedPrice);
                if (found != null) return found;
            }
        }
        return null;
    }


    // quick main to demonstrate Avocado lookup
    public static void main(String[] args) {
        List<Menu> menus = Arrays.asList(LUNCH_MENU, DRINK_MENU);

        String target = "Avocado";
        Double price = gp(menus, target);

        if (price != null) {
            System.out.println("Price of " + target + " = " + price);
        } else {
            System.out.println(target + " not found (or no price available).");
        }

        // extra prints to verify other items if you want
        System.out.println("Fries -> " + getPrice(menus, "Fries"));           // 3.5
        System.out.println("Fries -> " + gp(menus, "Fries"));           // 3.5

        System.out.println("Burger -> " + getPrice(menus, "Burger"));
        System.out.println("Burger -> " + gp(menus, "Burger"));         // 11.5 (menu fallback)

        // 11.5 (menu fallback)
        System.out.println("Iced Latte -> " + getPrice(menus, "Iced Latte")); // 6.5
        System.out.println("Iced Latte -> " + gp(menus, "Iced Latte")); // 6.5

        System.out.println("Sprite -> " + getPrice(menus, "Sprite"));
        System.out.println("Sprite -> " + gp(menus, "Sprite"));         // 2.5
        // 2.5
    }
}
