package Model;

public abstract class Part {
        private int id;
        private String name;
        private double price;
        private int stock;
        private int min;
        private int max;

    /**
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * Constructor for creating a part object.
     */
        public Part(int id, String name, double price, int stock, int min, int max) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.min = min;
            this.max = max;
        }


    /**
     * @return
     * Returns id of a Part object.
     */
        public int getId() {
            return id;
        }


    /**
     * @param id
     * Sets id of a Part object.
     */
        public void setId(int id) {
            this.id = id;
        }


    /**
     * @return
     * Returns String name of a Part object.
     */
        public String getName() {
            return name;
        }


    /**
     * @param name
     * Sets name  of a Part object.
     */
        public void setName(String name) {
            this.name = name;
        }


    /**
     * @return
     * Returns Double price of a Part object.
     */
        public double getPrice() {
            return price;
        }


    /**
     * @param price
     * Sets price of a Part object.
     */
        public void setPrice(double price) {
            this.price = price;
        }


    /**
     * @return
     * Returns Integer stock of a Part object.
     */
        public int getStock() {
            return stock;
        }


    /**
     * @param stock
     * Sets stock of a Part object.
     */
        public void setStock(int stock) {
            this.stock = stock;
        }


    /**
     * @return
     * Returns Integer min of a Part object.
     */
        public int getMin() {
            return min;
        }


    /**
     * @param min
     * Sets min of a Part object.
     */
        public void setMin(int min) {
            this.min = min;
        }


    /**
     * @return
     * Returns Integer max of a Part object.
     */
        public int getMax() {
            return max;
        }


    /**
     * @param max
     * Sets the max of a Part object.
     */
        public void setMax(int max) {
            this.max = max;
        }


}
