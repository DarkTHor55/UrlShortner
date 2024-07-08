package com.UrlShortner.Repository;

import com.UrlShortner.Model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url,Long> {

}
