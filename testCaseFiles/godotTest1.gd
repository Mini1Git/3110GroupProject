extends Control

@onready var ui = $".."

func open():
	visible = true
	Input.mouse_mode = Input.MOUSE_MODE_CONFINED

func close():
	visible = false
	Input.mouse_mode = Input.MOUSE_MODE_CAPTURED

func _on_character_pressed() -> void:
	visible = false
	ui.character.open()
	ui.openMenu = "character"

func _on_talents_pressed() -> void:
	visible = false
	ui.talents.open()
	ui.openMenu = "talent"

func _on_inventory_pressed() -> void:
	visible = false
	ui.inventory.open()
	ui.openMenu = "inventory"

func _on_magic_pressed() -> void:
	visible = false
	ui.magic.open()
	ui.openMenu = "magic"

func _on_settings_pressed() -> void:
	visible = false
	ui.settings.open()
	ui.openMenu = "settings"

func _on_close_pressed():
	close()
	ui.player.unpause()
	ui.openMenu = ""
