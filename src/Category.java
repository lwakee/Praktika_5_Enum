public enum Category {
    BLOCKNOTES("Блокноты"),
    BOOKS("Книги"),
    PENS("Ручки");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
