import React from 'react'
import { Dropdown } from 'semantic-ui-react'

const friendOptions = [
  {
    key: 'Photo1',
    text: 'Photo1',
    value: 'Photo1',
  },
  {
    key: 'Photo2',
    text: 'Photo2',
    value: 'Photo2',
  },
  {
    key: 'Photo3',
    text: 'Photo3',
    value: 'Photo3',
  }
]

const DropdownExampleSelection = () => (
  <Dropdown
    placeholder='Select Friend'
    fluid
    selection
    options={friendOptions}
  />
)

export default DropdownExampleSelection