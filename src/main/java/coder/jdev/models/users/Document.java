package coder.jdev.models.users;

import coder.jdev.exceptions.DocumentUnsupported;
import coder.jdev.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Document extends BaseEntity {

	@ManyToOne
	private User user;

	@Column(length = 50, nullable = false)
	private String name;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(nullable = false, columnDefinition = "LONGBLOB")
	private byte[] data;

	@Override
	public void prePersist() {
		super.prePersist();
		checkIsItSupportedDocument();
	}

	public void checkIsItSupportedDocument() {
		if (name == null) throw new DocumentUnsupported("document extension is not supported yet");
		int dot = name.lastIndexOf('.');
		if (dot == -1 || dot == name.length() - 1) throw new DocumentUnsupported("could not find an extension");
		String inputExtension = name.substring(dot + 1).toUpperCase();
		for (DocumentExtension documentExtension : DocumentExtension.values()) {
			if (documentExtension.name().equals(inputExtension)) return;
		}
		throw new DocumentUnsupported("document extension is not supported yet");
	}

}
