package layoutsControllers;

import javafx.beans.property.*;
import model.*;

import java.time.LocalDate;

public class SimplifiedData {
    protected SimpleStringProperty key;
    protected SimpleLongProperty id;
    protected SimpleStringProperty name;
    protected SimpleFloatProperty coordinate_x;
    protected SimpleIntegerProperty coordinate_y;
    protected SimpleStringProperty creationDate;
    protected SimpleLongProperty area;
    protected SimpleLongProperty numberOfRooms;
    protected SimpleBooleanProperty furniture;
    protected SimpleStringProperty furnish;
    protected SimpleStringProperty view;
    protected SimpleStringProperty name_of_house;
    protected SimpleLongProperty year_of_house;
    protected SimpleIntegerProperty numberOfFlatsOnFloor;
    protected SimpleStringProperty isYourElement;

    public SimplifiedData(String key, Flat flat){
        this.key = new SimpleStringProperty(key);
        id = new SimpleLongProperty(flat.getId());
        name = new SimpleStringProperty(flat.getName());
        coordinate_x = new SimpleFloatProperty(flat.getCoordinates().getX());
        coordinate_y = new SimpleIntegerProperty(flat.getCoordinates().getY());
        creationDate = new SimpleStringProperty(flat.getCreationDate().toString());
        area = new SimpleLongProperty(flat.getArea());
        numberOfRooms = new SimpleLongProperty(flat.getNumberOfRooms());
        furniture = new SimpleBooleanProperty(flat.isFurniture());
        furnish = new SimpleStringProperty(flat.getFurnish().toString());
        view = new SimpleStringProperty(flat.getView().toString());
        name_of_house = new SimpleStringProperty(flat.getHouse().getName());
        year_of_house = new SimpleLongProperty(flat.getHouse().getYear());
        numberOfFlatsOnFloor = new SimpleIntegerProperty(flat.getHouse().getNumberOfFlatsOnFloor());
        if(ScenesController.getInstance().users_elements_id.contains(flat.getId())){
            isYourElement = new SimpleStringProperty("+");
        }
        else{
            isYourElement = new SimpleStringProperty("-");
        }
    }

    public String getKey() {
        return key.get();
    }

    public SimpleStringProperty keyProperty() {
        return key;
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public float getCoordinate_x() {
        return coordinate_x.get();
    }

    public SimpleFloatProperty coordinate_xProperty() {
        return coordinate_x;
    }

    public void setCoordinate_x(float coordinate_x) {
        this.coordinate_x.set(coordinate_x);
    }

    public int getCoordinate_y() {
        return coordinate_y.get();
    }

    public SimpleIntegerProperty coordinate_yProperty() {
        return coordinate_y;
    }

    public void setCoordinate_y(int coordinate_y) {
        this.coordinate_y.set(coordinate_y);
    }

    public String getCreationDate() {
        return creationDate.get();
    }

    public SimpleStringProperty creationDateProperty() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate.set(creationDate);
    }

    public long getArea() {
        return area.get();
    }

    public SimpleLongProperty areaProperty() {
        return area;
    }

    public void setArea(long area) {
        this.area.set(area);
    }

    public long getNumberOfRooms() {
        return numberOfRooms.get();
    }

    public SimpleLongProperty numberOfRoomsProperty() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(long numberOfRooms) {
        this.numberOfRooms.set(numberOfRooms);
    }

    public boolean isFurniture() {
        return furniture.get();
    }

    public SimpleBooleanProperty furnitureProperty() {
        return furniture;
    }

    public void setFurniture(boolean furniture) {
        this.furniture.set(furniture);
    }

    public String getFurnish() {
        return furnish.get();
    }

    public SimpleStringProperty furnishProperty() {
        return furnish;
    }

    public void setFurnish(String furnish) {
        this.furnish.set(furnish);
    }

    public String getView() {
        return view.get();
    }

    public SimpleStringProperty viewProperty() {
        return view;
    }

    public void setView(String view) {
        this.view.set(view);
    }

    public String getName_of_house() {
        return name_of_house.get();
    }

    public SimpleStringProperty name_of_houseProperty() {
        return name_of_house;
    }

    public void setName_of_house(String name_of_house) {
        this.name_of_house.set(name_of_house);
    }

    public long getYear_of_house() {
        return year_of_house.get();
    }

    public SimpleLongProperty year_of_houseProperty() {
        return year_of_house;
    }

    public void setYear_of_house(long year_of_house) {
        this.year_of_house.set(year_of_house);
    }

    public String getIsYourElement() {
        return isYourElement.get();
    }

    public SimpleStringProperty isYourElementProperty() {
        return isYourElement;
    }

    public void setIsYourElement(String isYourElement) {
        this.isYourElement.set(isYourElement);
    }

    public int getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor.get();
    }

    public SimpleIntegerProperty numberOfFlatsOnFloorProperty() {
        return numberOfFlatsOnFloor;
    }

    public void setNumberOfFlatsOnFloor(int numberOfFlatsOnFloor) {
        this.numberOfFlatsOnFloor.set(numberOfFlatsOnFloor);
    }

    @Override
    public String toString() {
        return "Flat{" +
                "key=" + key.toString() +
                ", id=" + id.toString() +
                ", name=" + name.toString() +
                ", coordinate_x=" + coordinate_x.toString() +
                ", coordinate_y=" + coordinate_y.toString() +
                ", creationDate=" + creationDate.toString() +
                ", area=" + area.toString() +
                ", numberOfRooms=" + numberOfRooms.toString() +
                ", furniture=" + furniture.toString() +
                ", furnish=" + furnish.toString() +
                ", view=" + view.toString() +
                ", name_of_house=" + name_of_house.toString() +
                ", year_of_house=" + year_of_house.toString() +
                ", numberOfFlatsOnFloor=" + numberOfFlatsOnFloor.toString() +
                '}';
    }
}
