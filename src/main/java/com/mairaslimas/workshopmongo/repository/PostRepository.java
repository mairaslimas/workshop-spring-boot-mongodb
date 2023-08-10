package com.mairaslimas.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mairaslimas.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	List<Post> findByTitleContainingIgnoreCase(String Text);
	
	/*https://www.mongodb.com/docs/manual/reference/operator/query/regex/*/
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> findByTitle(String text);
	
	
	/*https://www.mongodb.com/docs/manual/reference/operator/query/regex/*/
	@Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } } ,{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
