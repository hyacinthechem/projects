import java.util.Collections;
import java.util.Set;

public class Official {

private String name;
private String title;
private Set<Official> teamOfficials;

public Official(String name, String title, Set<Official> teamOfficials) {
    this.name = name;
    this.title = title;
    this.teamOfficials = teamOfficials;
}

//official constructor with only the title role
    public Official(String title){
    this.title = title;
    }

public String getName() {
    return name;
}

public String getTitle() {
    return title;
}

public Set<Official> getTeamOfficials() {
    return Collections.unmodifiableSet(teamOfficials);
}

public void addTeamOfficial(Official official) {
    teamOfficials.add(official);
}

public void removeTeamOfficial(Official official) {
    teamOfficials.remove(official);
}

}
