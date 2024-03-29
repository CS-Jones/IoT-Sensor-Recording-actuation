



public class sensorData {

    private String name;
    private String value;
    private String id;


    @Override
    public String toString() {
        return "sensorData{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public sensorData(String name, String value, String id) {
        this.name = name;
        this.value = value;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


