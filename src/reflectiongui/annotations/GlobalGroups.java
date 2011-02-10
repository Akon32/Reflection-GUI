package reflectiongui.annotations;

/**
 * Указывает на принадлежность элемента к "глобальным" группам.
 * "Глобальная" группа идентифицируется только своим именем,
 * в отличие от "локальной" группы.
 *
 * @see Groups
 */
public @interface GlobalGroups {
    String[] value();
}
