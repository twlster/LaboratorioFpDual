package edu.fpdualjavafx.ejemplofx.persistence.model;

import org.bson.types.ObjectId;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public final class Log {

	private ObjectId id;
	@NonNull
	private String dateTime;
	@NonNull
	private String type;
	@NonNull
	private String text;

}
