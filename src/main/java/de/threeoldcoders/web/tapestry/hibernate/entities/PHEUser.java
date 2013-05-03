package de.threeoldcoders.web.tapestry.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

import org.hibernate.annotations.Index;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "USER")
@EqualsAndHashCode
@ToString
public class PHEUser
{
    @Id
    @GeneratedValue
    @Column(name = "PK")
    private @Getter @Setter Long pk;

    @Column(name = "NAME", length = 100, nullable = false, unique = true)
    @NotNull
    @Length(min = 2)
    @Index(name = "IDX_name")
    private @Getter @Setter String name;

    @Column(name = "PASSWORD", length = 256, nullable = false, unique = false)
    @NotNull
    @Length(min = 1)
    private @Getter @Setter String password;

    @Column(name = "EMAIL", length = 100, nullable = false, unique = true)
    @NotNull
    @Length(min = 2)
    @Index(name = "IDX_user_email")
    private @Getter String email;

    public PHEUser()
    {
    }

    public PHEUser(final String name, final String email, final String password)
    {
        this.name = name;
        this.password = password;

        setEmail(email);
    }
    
    public void setEmail(final String email)
    {
        this.email = (email!=null)?email.toLowerCase():null;
    }
}
