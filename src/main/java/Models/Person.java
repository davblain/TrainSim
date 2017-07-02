package Models;

/**
 * Created by Gemini on 01.07.2017.
 */
public class Person {

    private Boolean Want;
    private City currCity;
    private City nextCity;
    private Integer loadingDellay;
    private Integer unloadingDellay;

    public Integer getLoadingDellay() {
        return loadingDellay;
    }

    public void setLoadingDellay(Integer loadingDellay) {
        this.loadingDellay = loadingDellay;
    }

    public Integer getUnloadingDellay() {
        return unloadingDellay;
    }

    public void setUnloadingDellay(Integer unloadingDellay) {
        this.unloadingDellay = unloadingDellay;
    }

    public Boolean getWant() {
        return Want;
    }

    public void setWant(Boolean want) {
        Want = want;
    }
    public Boolean inThisCity(City city) {
        return nextCity == city;
    }
    public City getCurrCity() {
        return currCity;
    }

    public void setCurrCity(City currCity) {
        this.currCity = currCity;
    }

    public City getNextCity() {
        return nextCity;
    }

    public void setNextCity(City nextCity) {
        this.nextCity = nextCity;
    }
}
