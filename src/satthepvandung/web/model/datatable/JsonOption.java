package satthepvandung.web.model.datatable;

public abstract class JsonOption<T> {
	protected T record;

	public abstract String getDisplayText();

	public abstract Object getValue();

	public T getRecord() {
		return record;
	}

	public void setRecord(T record) {
		this.record = record;
	}

}
