package edu.fpdualjavafx.ejemplofx.persistence.model;

import org.bson.types.ObjectId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Log {

	private ObjectId _id;
	@NonNull
	private String dateTime;
	@NonNull
	private String type;
	@NonNull
	private String text;

}
