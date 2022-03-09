package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne  //멤버 입장에서 Many
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne  //일대일 양방향
    @JoinColumn(name = "LOCKER_ID")  //fk를 가지고 있으면 alter쿼리 나감
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);  //연관관계 편의 메서드
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public List<MemberProduct> getProducts() {
        return products;
    }

    public void setProducts(List<MemberProduct> products) {
        this.products = products;
    }
}
