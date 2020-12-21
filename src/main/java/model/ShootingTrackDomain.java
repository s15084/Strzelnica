package model;

import org.hibernate.annotations.GenericGenerator;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShootingTrackDomain {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private domain_Name domainName;

    //asocjacje
    @OneToMany
    private List<ShootingTrack> shootingTrackList = new ArrayList<>();

    public enum domain_Name{
        SKEET, TRAP, DOUBLE_TRAP;
    }

    public void addShootingTrack(ShootingTrack shootingTrack){
        if(!shootingTrackList.contains(shootingTrack)){
            shootingTrackList.add(shootingTrack);
            shootingTrack.setShootingTrackDomain(this);
        }
    }

    public void removeShootingTrack(ShootingTrack shootingTrack){
        if(shootingTrackList.contains(shootingTrack)){
            this.shootingTrackList.remove(shootingTrack);
            shootingTrack.setShootingTrackDomain(null);
        }
    }

    public domain_Name getDomainName() {
        return domainName;
    }

    public void setDomainName(domain_Name domainName) {
        this.domainName = domainName;
    }

    public List<ShootingTrack> getShootingTrackList() {
        return shootingTrackList;
    }

    public void setShootingTrackList(List<ShootingTrack> shootingTrackList) {
        this.shootingTrackList = shootingTrackList;
    }
}
